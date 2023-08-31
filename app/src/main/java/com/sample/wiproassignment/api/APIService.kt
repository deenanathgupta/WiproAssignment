package com.sample.wiproassignment.api

import com.sample.wiproassignment.model.AlbumResponseModelItem
import com.sample.wiproassignment.network.NetworkResult
import retrofit2.http.GET

interface APIService {
    @GET("posts")
    suspend fun getAlbumList(): NetworkResult<List<AlbumResponseModelItem>>
}