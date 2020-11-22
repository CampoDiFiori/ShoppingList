package com.dudko.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class OptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        this.title = "Settings"
        val sharedPreferences = getSharedPreferences("shopping_options", Context.MODE_PRIVATE)
        val darkTheme = sharedPreferences.getBoolean("dark_theme", false)
        val biggerTextSize = sharedPreferences.getBoolean("bigger_text_size", false)

        val themeGroup = findViewById<RadioGroup>(R.id.themeRadioGroup)
        val textSizeGroup = findViewById<RadioGroup>(R.id.textSizeRadioGroup)

        when (darkTheme) {
            true -> {
                themeGroup.check(R.id.darkTheme)
            }
            false -> {
                themeGroup.check(R.id.lightTheme)
            }
        }

        when (biggerTextSize) {
            true -> {
                textSizeGroup.check(R.id.biggerText)
            }
            false -> {
                textSizeGroup.check(R.id.smallerText)
            }
        }
    }

    fun onSettingsClickOk(view: View) {
        finish()
    }

    fun onThemeChange(view: View) {
        val sharedPreferences = getSharedPreferences("shopping_options", Context.MODE_PRIVATE)
        val spEditor = sharedPreferences.edit()
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.darkTheme -> {
                    if (checked) {
                        spEditor.putBoolean("dark_theme", true)
                        setTheme(R.style.ThemeOverlay_AppCompat_Dark)
                    }
                }
                R.id.lightTheme -> {
                    if (checked) {
                        spEditor.putBoolean("dark_theme", false)
                        setTheme(R.style.ThemeOverlay_AppCompat_Light)
                    }
                }
            }
            spEditor.apply()
        }
    }

    fun onTextSizeChange(view: View) {
        val sharedPreferences = getSharedPreferences("shopping_options", Context.MODE_PRIVATE)
        val spEditor = sharedPreferences.edit()
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.biggerText -> {
                    if (checked) {
                        spEditor.putBoolean("bigger_text_size", true)
                    }
                }
                R.id.smallerText -> {
                    if (checked) {
                        spEditor.putBoolean("bigger_text_size", false)
                    }
                }
            }
            spEditor.apply()
        }
    }
}