package com.dudko.shoppinglist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ProductListActivity : AppCompatActivity() {

    private lateinit var shoppingListViewModel: ShoppingItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.shopping_list)
        val rv = findViewById<RecyclerView>(R.id.shopping_list)
        rv.layoutManager = LinearLayoutManager(this)

        shoppingListViewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(this.application))
                .get(ShoppingItemViewModel::class.java)
        shoppingListViewModel.allItems.observe(this, Observer { people ->
            people?.let {
                (rv.adapter as ShoppingListAdapter).setShoppingList(it)
            }
        })
        rv.adapter = ShoppingListAdapter(this, shoppingListViewModel)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, EditProductActivity::class.java)
            startActivity(intent)
        }

    }
}