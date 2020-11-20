package com.dudko.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ShoppingListActivity : AppCompatActivity() {

    private lateinit var shoppingListViewModel: ShoppingItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
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

    }
}