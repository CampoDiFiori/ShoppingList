package com.dudko.shoppinglist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ShoppingItemViewModel(application: Application) : AndroidViewModel(application) {
    private val itemDao: ShoppingItemDao = ShoppingDB.getDatabase(application).shoppingItemDao()
    val allItems: LiveData<List<ShoppingItem>> = itemDao.getAllItems()

    fun insert(item: ShoppingItem) = itemDao.insert(item)
    fun update(item: ShoppingItem) = itemDao.update(item)
    fun delete(item: ShoppingItem) = itemDao.delete(item)
    fun deleteAll() = itemDao.deleteAll()
}