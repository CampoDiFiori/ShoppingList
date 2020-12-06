package com.dudko.shoppinglist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Database(entities = [ShoppingItem::class], version = 1)
public abstract class ShoppingDB : RoomDatabase() {
    abstract fun shoppingItemDao(): ShoppingItemDao

    companion object{
        private var instance: ShoppingDB? = null
        fun getDatabase(context: Context): ShoppingDB{
            val tmpInst = instance
            if(tmpInst != null){
                return tmpInst
            }
            val inst = Room.databaseBuilder(
                context.applicationContext,
                ShoppingDB::class.java,
                "shopping_db"
            ).allowMainThreadQueries().build()
            instance = inst
            return inst
        }
    }
}

@Entity(tableName = "shopping_item")
data class ShoppingItem(var name: String, var price: Float, var quantity: Int, var checked: Boolean = false) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Dao
interface ShoppingItemDao {
    @Query("SELECT * FROM shopping_item")
    fun getAllItems(): LiveData<List<ShoppingItem>>

    @Insert
    fun insert(item: ShoppingItem)

    @Update
    fun update(item: ShoppingItem)

    @Delete
    fun delete(item: ShoppingItem)

    @Query("DELETE FROM shopping_item WHERE shopping_item.id = :idd")
    fun deleteById(idd: Long)

    @Query("DELETE FROM shopping_item")
    fun deleteAll()

    @Query("SELECT id, name, price, quantity, checked FROM shopping_item where shopping_item.id = :idd")
    fun getItemById(idd: Long): ShoppingItem?

    @Query("SELECT id, name, price, quantity, checked FROM shopping_item ORDER BY id DESC LIMIT 1")
    fun getLastItem(): ShoppingItem?
}