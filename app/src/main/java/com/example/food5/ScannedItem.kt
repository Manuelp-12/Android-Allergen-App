package com.example.food5

import android.net.Uri

interface ProductInfo {
    val product: String
}
data class ScannedItem (
    override val product : String,
    val brand : String,
    val image : String,
    var allergens : String,
    val ingredients : String
) : ProductInfo

data class DetectedItem (
    override val product : String,
    val confidence : Int,
    val image : Uri,
    var mainAllergens : String,
    var potentialAllergens : String
) : ProductInfo