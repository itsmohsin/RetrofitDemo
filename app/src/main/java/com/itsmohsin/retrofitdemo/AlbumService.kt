package com.itsmohsin.retrofitdemo

import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
    @GET("/albums")
//    using kotlin coroutines so suspend is used here
    suspend fun getAlbums() : Response<Albums>
}