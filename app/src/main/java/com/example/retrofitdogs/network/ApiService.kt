package com.example.retrofitdogs.network

import com.example.retrofitdogs.DogsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val BASE_URL = "https://dog.ceo/api/"
    }

    @GET("breed/{breed}/images")
    fun getDogsByBreed(@Path("breed") breed: String): Call<DogsResponse>
}
