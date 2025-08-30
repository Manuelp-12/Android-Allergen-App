package com.example.food5

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.Locale

class DetectedProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detected_product)

        val topToolbar: Toolbar = findViewById(R.id.topToolbar)
        setSupportActionBar(topToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        topToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        topToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val img : ImageView = findViewById(R.id.imageView)
        val productView : TextView = findViewById(R.id.productTextView)
        val mainAllergens: TextView = findViewById(R.id.mainAllergensTextView)
        val potentialAllergens: TextView = findViewById(R.id.potentialAllergensTextView)

        val intent = intent
        var product = intent.getStringExtra("product")
        val confidence = intent.getIntExtra("confidence", 0)
        product?.replace("_", " ")
        product = formatProduct(product)

        product += "\n(${confidence}% confidence)"
        var main = "Main allergens: " + intent.getStringExtra("mainAllergens")
        var potential = "Potential allergens: " + intent.getStringExtra("potentialAllergens")

        val image: Uri? = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("image", Uri::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("image")
        }

        var riskImg = findViewById<ImageView>(R.id.riskimg)
        var riskText = findViewById<TextView>(R.id.riskText)

        riskImg.setImageResource(R.drawable.green)
        riskText.text = "Risk: Low"

        val potentialAllergenWords = (potential).split(",").map { it.trim() }
        for (word in potentialAllergenWords) {
            if (SharedAllergens.selectedItems.any { it.equals(word, ignoreCase = true) }) {
                riskText.text = "Risk: Medium"
                riskImg.setImageResource(R.drawable.yellow)
            }
        }

        val mainAllergenWords = (main).split(",").map { it.trim() }
        for (word in mainAllergenWords) {
            if (SharedAllergens.selectedItems.any { it.equals(word, ignoreCase = true) }) {
                riskText.text = "Risk: High"
                riskImg.setImageResource(R.drawable.red)
            }
        }

        productView.text = product
        mainAllergens.text = main
        potentialAllergens.text = potential
        img.setImageURI(image)
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