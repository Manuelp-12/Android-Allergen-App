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

class HistoryAdapter(private val historyList : ArrayList<ScannedItem>): RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {
    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val productImageView : ImageView = itemView.findViewById(R.id.productimg)
        val productText : TextView = itemView.findViewById(R.id.productname)
        val brandText : TextView = itemView.findViewById(R.id.productbrand)
        val riskImageView : ImageView = itemView.findViewById(R.id.risk_image_recycler)
        val riskText : TextView = itemView.findViewById(R.id.risk_text_recycler)
        val btn : ImageButton = itemView.findViewById(R.id.infobtn)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history_recycler_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = historyList[position]
        val imageUrl = currentItem.image

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.baseline_image_24)
            .error(R.drawable.baseline_image_24)
            .into(holder.productImageView)
        //holder.productImageView.setImageResource(currentItem.image)

        holder.productText.text = currentItem.product
        holder.brandText.text = currentItem.brand
        holder.riskImageView.setImageResource(R.drawable.green)
        holder.riskText.text = "Risk: Low"

        val allergenWords =
            currentItem.allergens.split(",", ignoreCase = true, limit = 0).map { it.trim() }

        for (word in allergenWords) {
            if (SharedAllergens.selectedItems.any { it.equals(word, ignoreCase = true) }) {
                holder.riskText.text = "Risk: High"
                holder.riskImageView.setImageResource(R.drawable.red)
            }
        }

        holder.btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.itemView.context, ScannedProductActivity::class.java)
            intent.putExtra("product", currentItem.product)
            intent.putExtra("brand", currentItem.brand)
            intent.putExtra("imageurl", currentItem.image)
            intent.putExtra("product", currentItem.product)
            intent.putExtra("allergens", currentItem.allergens)
            intent.putExtra("ingredients", currentItem.ingredients)
            holder.itemView.context?.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

}