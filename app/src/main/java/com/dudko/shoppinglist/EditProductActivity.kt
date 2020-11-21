package com.dudko.shoppinglist

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class EditProductActivity: AppCompatActivity() {

    private lateinit var shoppingListViewModel: ShoppingItemViewModel
    private var editedProductId: Long = 0 // if it's 0, we're adding a new product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_product)

        shoppingListViewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(this.application))
                .get(ShoppingItemViewModel::class.java)

        editedProductId = intent.getLongExtra("id", 0)

        if (editedProductId == 0L) {
            this.title = "Add a product"
        } else {
            this.title = "Edit product"

            val nameContent = findViewById<EditText>(R.id.itemName)
            val priceContent = findViewById<EditText>(R.id.itemPrice)
            val quantityContent = findViewById<EditText>(R.id.itemQuantity)

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
        val price = findViewById<EditText>(R.id.itemPrice).text.toString().toFloat()
        val quantity = findViewById<EditText>(R.id.itemQuantity).text.toString().toInt()

        if (editedProductId == 0L) {
            shoppingListViewModel.insert(ShoppingItem(name, price, quantity))
        } else {
            val editedProduct = shoppingListViewModel.getItemById(editedProductId)
            println("Product: ${editedProduct}")
            if (editedProduct != null) {
                shoppingListViewModel.update(editedProduct.copy(name = name, price = price, quantity = quantity))
            } else {
                Toast.makeText(this, "Sorry, couldn't update the product $editedProductId", Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }

    fun onCancelClick(view: View) {
        finish()
    }


}