package com.example.food5

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HistoryAdapter(private val historyList : ArrayList<ProductInfo>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_SCANNED = 0
        private const val VIEW_TYPE_DETECTED = 1
    }

    class ScannedViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val productImageView : ImageView = itemView.findViewById(R.id.productimg)
        val productText : TextView = itemView.findViewById(R.id.productname)
        val brandText : TextView = itemView.findViewById(R.id.productbrand)
        val riskImageView : ImageView = itemView.findViewById(R.id.risk_image_recycler)
        val riskText : TextView = itemView.findViewById(R.id.risk_text_recycler)
        val btn : ImageButton = itemView.findViewById(R.id.infobtn)
    }
    class DetectedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImageView: ImageView = itemView.findViewById(R.id.productimg)
        val productText: TextView = itemView.findViewById(R.id.productname)
        val riskImageView: ImageView = itemView.findViewById(R.id.risk_image_recycler)
        val riskText: TextView = itemView.findViewById(R.id.risk_text_recycler)
        val btn: ImageButton = itemView.findViewById(R.id.infobtn)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history_recycler_row, parent, false)
//        return ScannedViewHolder(itemView)
        return when (viewType) {
            VIEW_TYPE_SCANNED -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.history_recycler_row, parent, false)
                ScannedViewHolder(view)
            }
            VIEW_TYPE_DETECTED -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.detected_product_row, parent, false)
                DetectedViewHolder(view)
            }
            else -> throw IllegalArgumentException("Unknown viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (val item = historyList[position]) {
            is ScannedItem -> {
                val scannedHolder = holder as ScannedViewHolder

                Glide.with(holder.itemView.context)
                    .load(item.image)
                    .placeholder(R.drawable.baseline_image_24)
                    .error(R.drawable.baseline_image_24)
                    .into(scannedHolder.productImageView)
                //holder.productImageView.setImageResource(currentItem.image)

                scannedHolder.productText.text = item.product
                scannedHolder.brandText.text = item.brand
                scannedHolder.riskImageView.setImageResource(R.drawable.green)
                scannedHolder.riskText.text = "Risk: Low"

                val allergenWords = item.allergens.split(",", ignoreCase = true, limit = 0).map { it.trim() }

                for (word in allergenWords) {
                    if (SharedAllergens.selectedItems.any { it.equals(word, ignoreCase = true) }) {
                        scannedHolder.riskText.text = "Risk: High"
                        scannedHolder.riskImageView.setImageResource(R.drawable.red)
                    }
                }

                scannedHolder.btn.setOnClickListener {
                    val intent = Intent(holder.itemView.context, ScannedProductActivity::class.java)
                    intent.putExtra("product", item.product)
                    intent.putExtra("brand", item.brand)
                    intent.putExtra("imageurl", item.image)
                    intent.putExtra("product", item.product)
                    intent.putExtra("allergens", item.allergens)
                    intent.putExtra("ingredients", item.ingredients)
                    scannedHolder.itemView.context?.startActivity(intent)
                }
            }

            is DetectedItem -> {
                val detectedHolder = holder as DetectedViewHolder

                Glide.with(detectedHolder.itemView.context)
                    .load(item.image) // item.image is a Uri, Glide handles it
                    .placeholder(R.drawable.baseline_image_24)
                    .error(R.drawable.baseline_image_24)
                    .into(detectedHolder.productImageView)

                detectedHolder.productText.text = item.product
                detectedHolder.riskImageView.setImageResource(R.drawable.green)
                detectedHolder.riskText.text = "Risk: Low"

                val potentialAllergenWords = (item.potentialAllergens).split(",").map { it.trim() }
                for (word in potentialAllergenWords) {
                    if (SharedAllergens.selectedItems.any { it.equals(word, ignoreCase = true) }) {
                        detectedHolder.riskText.text = "Risk: Medium"
                        detectedHolder.riskImageView.setImageResource(R.drawable.yellow)
                    }
                }

                val mainAllergenWords = (item.mainAllergens).split(",").map { it.trim() }
                for (word in mainAllergenWords) {
                    if (SharedAllergens.selectedItems.any { it.equals(word, ignoreCase = true) }) {
                        detectedHolder.riskText.text = "Risk: High"
                        detectedHolder.riskImageView.setImageResource(R.drawable.red)
                    }
                }

                detectedHolder.btn.setOnClickListener {
                    val intent = Intent(detectedHolder.itemView.context, DetectedProductActivity::class.java)
                    intent.putExtra("product", item.product)
                    intent.putExtra("confidence", item.confidence)
                    intent.putExtra("mainAllergens", item.mainAllergens)
                    intent.putExtra("potentialAllergens", item.potentialAllergens)
                    intent.putExtra("image", item.image)
                    detectedHolder.itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (historyList[position]) {
            is ScannedItem -> VIEW_TYPE_SCANNED
            is DetectedItem -> VIEW_TYPE_DETECTED
            else -> throw IllegalArgumentException("Unknown type")
        }
    }

}