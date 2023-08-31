package com.sample.wiproassignment.api

import com.sample.wiproassignment.model.AlbumResponseModelItem
import com.sample.wiproassignment.network.NetworkResult
import javax.inject.Inject

class AlbumRemoteDataSource @Inject constructor(private val service: APIService) :
    IAlbumDataSource {
    override suspend fun getAlbumList(): NetworkResult<List<AlbumResponseModelItem>> {
        return service.getAlbumList()
    }
}