package com.sample.wiproassignment.domain

import com.android.hilt_coroutine_retrofit_adapter_demo.repository.AlbumRepository
import com.sample.wiproassignment.di.IoDispatcher
import com.sample.wiproassignment.network.onError
import com.sample.wiproassignment.network.onException
import com.sample.wiproassignment.network.onSuccess
import com.sample.wiproassignment.viewmodel.AlbumViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAlbumUseCase @Inject constructor(
    private val repository: AlbumRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<Any, AlbumViewState>(ioDispatcher) {
    override fun execute(parameters: Any): Flow<AlbumViewState> = flow {
        emit(AlbumViewState.Loading)
        repository.getAlbumList().onSuccess {
            emit(AlbumViewState.Success(it))
        }.onException {
            emit(AlbumViewState.Exception(it))
        }.onError { code, message ->
            emit(AlbumViewState.Error(code, message))
        }
    }
}