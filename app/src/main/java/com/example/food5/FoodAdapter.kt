package com.example.food5

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter (private var foodList: ArrayList<Food>, private val viewModel: SharedViewModel) : RecyclerView.Adapter<FoodAdapter.MyViewHolder>() {

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val textView : TextView = itemView.findViewById(R.id.textView)
        val cardView : com.google.android.material.card.MaterialCardView = itemView.findViewById(R.id.foodCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_recycler_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = foodList[position]
        holder.textView.text = currentItem.foodName

        if (viewModel.isSelected(currentItem.foodName)) {
            holder.cardView.setBackgroundColor(Color.GREEN)
        } else {
            holder.cardView.setBackgroundColor(Color.TRANSPARENT)
        }

        holder.itemView.setOnClickListener {
            viewModel.toggleItem(currentItem.foodName)
            notifyItemChanged(holder.adapterPosition)
        }
    }

    fun setFilteredList(foodArrayList : ArrayList<Food>) {
        this.foodList = foodArrayList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}