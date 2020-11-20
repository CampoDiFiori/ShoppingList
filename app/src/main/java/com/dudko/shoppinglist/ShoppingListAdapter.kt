package com.dudko.shoppinglist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ShoppingListAdapter(val context: Context, val shoppingListViewModel: ShoppingItemViewModel)
    : RecyclerView.Adapter<ShoppingListAdapter.ShoppingItemHolder>()
{
    private var items = emptyList<ShoppingItem>()

    inner class ShoppingItemHolder(val view: View)
        : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingItemHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingItemHolder, position: Int) {
        val currentItem = items[position]
        val title = holder.view.findViewById<TextView>(R.id.itemName)
        val price = holder.view.findViewById<TextView>(R.id.itemPrice)
        val checked = holder.view.findViewById<CheckBox>(R.id.checked)

        title.text = "${currentItem.name} (${currentItem.quantity})"
        price.text = currentItem.price.toString()
        checked.isChecked = currentItem.checked

        holder.view.setOnClickListener {
            Toast.makeText(it.context, "Id: ${currentItem.id}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal fun setShoppingList(items: List<ShoppingItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}