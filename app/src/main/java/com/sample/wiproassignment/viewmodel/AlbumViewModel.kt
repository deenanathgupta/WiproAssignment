package com.sample.wiproassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.wiproassignment.model.AlbumResponseModelItem
import com.sample.wiproassignment.R
import com.sample.wiproassignment.domain.GetAlbumUseCase
import com.sample.wiproassignment.utils.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val context: Application,
    private val albumUseCase: GetAlbumUseCase
) : AndroidViewModel(context) {
    private val _albumViewState =
        MutableStateFlow<AlbumViewState>(AlbumViewState.Idle)
    val albumUiState = _albumViewState.asStateFlow()

    fun getAlbumList() {
        viewModelScope.launch {
            if (context.hasInternetConnection().not()) {
                _albumViewState.value = AlbumViewState.NoInternet
            } else {
                albumUseCase.invoke(String()).collect {
                    _albumViewState.value = it
                }
            }
        }
    }
}

sealed class AlbumViewState {
    object Idle : AlbumViewState()
    object Loading : AlbumViewState()
    object NoInternet : AlbumViewState()
    data class Success(val data: List<AlbumResponseModelItem>) : AlbumViewState()
    data class Error(val errorCode: Int, val errorMessage: String?) : AlbumViewState()
    data class Exception(val e: Throwable) : AlbumViewState()
    fun loading() = this is Loading
    fun noInternet() = this is NoInternet
}