package com.sample.wiproassignment.api

import com.sample.wiproassignment.model.AlbumResponseModelItem
import com.sample.wiproassignment.network.NetworkResult


interface IAlbumDataSource {

    suspend fun getAlbumList(): NetworkResult<List<AlbumResponseModelItem>>
}