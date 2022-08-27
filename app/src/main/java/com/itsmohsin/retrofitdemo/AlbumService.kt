package com.itsmohsin.retrofitdemo

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {
    @GET("/albums")
//    using kotlin coroutines so suspend is used here
    suspend fun getAlbums() : Response<Albums>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId:Int) : Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id")albumId:Int) : Response<AlbumsItem>
}