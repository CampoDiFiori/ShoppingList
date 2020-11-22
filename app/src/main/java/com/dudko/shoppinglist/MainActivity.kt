package com.dudko.shoppinglist

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSettingsClick(view: View) {
        val optionsActivityIntent = Intent(this, OptionsActivity::class.java)
        startActivity(optionsActivityIntent)
    }

    fun onYourListClick(view: View) {
        val productListActivityIntent = Intent(this, ProductListActivity::class.java)
        startActivity(productListActivityIntent)
    }

    fun setTheme() {

    }
}