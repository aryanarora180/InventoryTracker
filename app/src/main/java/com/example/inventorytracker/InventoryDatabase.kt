package com.example.inventorytracker

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [InventoryItem::class], version = 1)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun inventoryDao() : InventoryDao

}