package com.example.pixabayimageapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("api/")
    fun getImages(
        @Query("key") apiKey: String,
        @Query("q") query: String
    ): Call<PixabayImageResponse>
}
