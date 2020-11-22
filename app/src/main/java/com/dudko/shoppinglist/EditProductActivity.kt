package com.dudko.shoppinglist

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
                println(newItem)
                shoppingListViewModel.insert(newItem)
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

}