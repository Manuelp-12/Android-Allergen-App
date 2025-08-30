package com.example.food5

import android.os.Bundle
import android.text.SpannableString
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import java.util.Locale

class ScannedProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scanned_product)

        val topToolbar: Toolbar = findViewById(R.id.topToolbar)
        setSupportActionBar(topToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        topToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        topToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val img : ImageView = findViewById(R.id.imageView)
        val productView : TextView = findViewById(R.id.productTextView)
        val brandView : TextView = findViewById(R.id.brandTextView)
        val allergenView : TextView = findViewById(R.id.allergensTextView)
        val ingredientView : TextView = findViewById(R.id.ingredientsTextView)

        val intent = intent
        val product = intent.getStringExtra("product")
        val brand = intent.getStringExtra("brand")
        val imageurl = intent.getStringExtra("imageurl")
        val ingredients = "Ingredients: " + intent.getStringExtra("ingredients")

        var riskImg = findViewById<ImageView>(R.id.riskimg)
        var riskText = findViewById<TextView>(R.id.riskText)

        riskImg.setImageResource(R.drawable.green)
        riskText.text = "Risk: Low"

        val allergensRaw = intent.getStringExtra("allergens") ?: ""

        //text before allergens
        val prefix = "Key allergens: "

        // Create a SpannableString
        val spannable = SpannableString(prefix + allergensRaw)

        // Offset where the actual allergens text starts
        val startOffset = prefix.length

        // Split allergens by commas
        val allergenWords = allergensRaw.split(",", ignoreCase = true, limit = 0).map { it.trim() }

        val lowercasedSelected = SharedAllergens.selectedItems.map { it.lowercase() }.toSet()

        for (word in allergenWords) {
            val lowerWord = word.lowercase()

            if (lowercasedSelected.any { lowerWord.contains(it) }) {
                riskText.text = "Risk: High"
                riskImg.setImageResource(R.drawable.red)

                val startIndex = spannable.toString()
                    .indexOf(word, startIndex = startOffset, ignoreCase = true)

                if (startIndex != -1) {
                    spannable.setSpan(
                        android.text.style.BackgroundColorSpan(android.graphics.Color.YELLOW),
                        startIndex,
                        startIndex + word.length,
                        android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
        }

        allergenView.text = spannable

        Glide.with(this)
            .load(imageurl)
            .placeholder(R.drawable.baseline_image_24)
            .error(R.drawable.baseline_image_24)
            .into(img)

        productView.setText(product)
        brandView.setText(brand)
        ingredientView.setText(ingredients)
    }
    fun formatProduct(str: String?): String {
        if (str == null) {
            return ""
        }
        val sb = StringBuilder(
            str.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            }
        )

        var i = 0
        while (i < sb.length) {
            if (sb[i] == '_') {
                sb.setCharAt(i, ' ') // replace _ with space
                if (i + 1 < sb.length) {
                    sb.setCharAt(i + 1, sb[i + 1].uppercaseChar()) // capitalize next char
                }
            }
            i++
        }
        return sb.toString()
    }
}