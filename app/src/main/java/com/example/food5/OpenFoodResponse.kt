package com.example.food5

data class OpenFoodResponse(
    val code: String?,
    val product: Product?,
    val status: Int,
    val status_verbose: String
)

data class Product(
    val product_name: String?,
    val brands: String?,
    val image_url: String?,
    val ingredients_text: String?,
    val allergens_tags: List<String>?,
)