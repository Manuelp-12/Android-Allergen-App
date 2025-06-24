package com.example.food5

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenFoodApi {
    @GET("api/v0/product/{barcode}.json")
    fun getProductByBarcode(@Path("barcode") barcode: String): Call<OpenFoodResponse>
}