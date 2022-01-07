package com.example.inventorytracker

import androidx.room.*

@Dao
interface InventoryDao {

    @Insert
    fun insert(item: InventoryItem)

    @Query("SELECT * FROM inventory")
    fun getAll(): List<InventoryItem>

    @Delete
    fun delete(item: InventoryItem)

    @Update
    fun update(item: InventoryItem)

}