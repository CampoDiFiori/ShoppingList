package com.dudko.shoppinglist

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class EditProductActivity: BaseActivity() {

    private lateinit var shoppingListViewModel: ShoppingItemViewModel
    private var editedProductId: Long? = null // if it's null, we're adding a new product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_product)

        shoppingListViewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(this.application))
                .get(ShoppingItemViewModel::class.java)

        intent.getLongExtra("id", 0).let {
            editedProductId = if (it == 0L) {
                null
            } else {
                it
            }
        }

        val nameContent = findViewById<EditText>(R.id.itemName)
        val priceContent = findViewById<EditText>(R.id.itemPrice)
        val quantityContent = findViewById<EditText>(R.id.itemQuantity)

        if (editedProductId == null) {
            this.title = "Add a product"
        } else {
            this.title = "Edit product"

            val passedName = intent.getStringExtra("name")
            val passedPrice = intent.getFloatExtra("price", 0.0f)
            val passedQuantity = intent.getIntExtra("quantity", 0)

            nameContent.setText(passedName)
            priceContent.setText(passedPrice.toString())
            quantityContent.setText(passedQuantity.toString())
        }
    }

    fun onOKClick(view: View) {
        val name = findViewById<EditText>(R.id.itemName).text.toString()
        val priceString = findViewById<EditText>(R.id.itemPrice).text.toString()
        val quantityString = findViewById<EditText>(R.id.itemQuantity).text.toString()

        if (name.isEmpty() || priceString.isEmpty() || quantityString.isEmpty()) {
            Toast.makeText(this, "Fill out all the inputs first", Toast.LENGTH_SHORT).show()
            return
        }

        val price = priceString.toFloat()
        val quantity = quantityString.toInt()

        when (editedProductId) {
            null -> {
                val newItem = ShoppingItem(name = name, price = price, quantity = quantity, checked = false)
                shoppingListViewModel.insert(newItem)
                val newItemWithId = shoppingListViewModel.getLastItem()
                onItemAdd(newItemWithId!!)
            }
            else -> {
                val editedProduct = shoppingListViewModel.getItemById(editedProductId!!)
                if (editedProduct != null) {
                    editedProduct.name = name
                    editedProduct.price = price
                    editedProduct.quantity = quantity

                    shoppingListViewModel.update(editedProduct)
                } else {
                    Toast.makeText(this, "Sorry, couldn't update the product $editedProductId", Toast.LENGTH_SHORT).show()
                }
            }
        }
        finish()
    }

    fun onCancelClick(view: View) {
        finish()
    }

    private fun onItemAdd(item: ShoppingItem) {
        val launchEditItemIntent = Intent(this, EditProductActivity::class.java).apply {
            putExtra("id", item.id)
            putExtra("name", item.name)
            putExtra("price", item.price)
            putExtra("quantity", item.quantity)
        }
        val pendingIntent = PendingIntent.getActivity(
                this,
                1,
                launchEditItemIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        val broadcastIntent = Intent("com.dudko.ITEM_ADDED").apply {
            this.putExtra("edit_item_activity", pendingIntent)
            putExtra("notification_message", "${item.name} (${item.quantity})")
        }
        sendBroadcast(broadcastIntent, "com.dudko.ITEM_ADDED_PERMISSION")
    }

}