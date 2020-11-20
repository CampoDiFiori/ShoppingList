package com.dudko.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ExampleActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView;
    private lateinit var viewAdapter: RecyclerView.Adapter<*>;
    private lateinit var viewManager: RecyclerView.LayoutManager;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)

        val shopItems = ArrayList<ShopItem>();
        shopItems.add(ShopItem(1, "Milk", 12.5f, 1u))
        shopItems.add(ShopItem(2, "Bread", 3.1f, 2u))
        shopItems.add(ShopItem(3, "Butter", 5.0f, 2u))
        shopItems.add(ShopItem(4, "Orange Juice", 2.99f, 1u))

        viewAdapter = MyAdapter(shopItems)

        recyclerView = findViewById<RecyclerView>(R.id.shopping_list).apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }



}