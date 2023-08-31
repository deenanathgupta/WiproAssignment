package com.sample.wiproassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.wiproassignment.di.RepositoryModule
import com.sample.wiproassignment.model.AlbumResponseModelItem
import com.sample.wiproassignment.network.onError
import com.sample.wiproassignment.network.onException
import com.sample.wiproassignment.network.onSuccess
import com.android.hilt_coroutine_retrofit_adapter_demo.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val repository: AlbumRepository,
    @RepositoryModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private var _albumList = MutableLiveData<List<AlbumResponseModelItem>>()
    val albumList: LiveData<List<AlbumResponseModelItem>>
        get() = _albumList

    private var _isErrorHappen = MutableLiveData<Boolean>()
    val isErrorHappen: LiveData<Boolean>
        get() = _isErrorHappen

    fun getAlbumList() {
        viewModelScope.launch(ioDispatcher) {
            val response = repository.getAlbumList()
            response.onSuccess {
                _albumList.postValue(it)
            }
            response.onError { _, _ ->
                _isErrorHappen.postValue(true)
            }
            response.onException {
                _isErrorHappen.postValue(true)
            }
        }
    }
}