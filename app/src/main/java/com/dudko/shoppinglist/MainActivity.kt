package com.dudko.shoppinglist

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("shopping_options", Context.MODE_PRIVATE)
        when (sharedPreferences.getBoolean("dark_theme", false)) {
            true -> {
                setTheme(R.style.ThemeOverlay_AppCompat_Dark_ActionBar)
            }
            false -> {
                setTheme(R.style.ThemeOverlay_AppCompat_Light)
            }
        }
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
}