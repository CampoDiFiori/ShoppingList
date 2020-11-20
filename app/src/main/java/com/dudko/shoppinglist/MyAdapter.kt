package com.dudko.shoppinglist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes

data class ShopItem(val id: Int, val name: String, val price: Float, val quantity: UInt, val bought: Boolean = false)

class MyAdapter(private val shopItemList: ArrayList<ShopItem>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ViewHolder(v, v.context)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        val currentItem = shopItemList[position]
        holder.bindItems(currentItem)
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Id: ${currentItem.id}", Toast.LENGTH_SHORT).show()
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return shopItemList.size
    }


    //the class is hodling the list view
    class ViewHolder(itemView: View, var context: android.content.Context) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(shopItem: ShopItem) {
            val textViewName = itemView.findViewById(R.id.itemName) as TextView
            val textViewAddress  = itemView.findViewById(R.id.itemPrice) as TextView
            textViewName.text = shopItem.name
            textViewAddress.text = shopItem.price.toString()
        }
    }
}