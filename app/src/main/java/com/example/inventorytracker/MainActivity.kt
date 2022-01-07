package com.example.inventorytracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.inventorytracker.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val _adapter = InventoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            InventoryDatabase::class.java, "inventory"
        ).build()

        binding.inventoryRecycler.adapter = _adapter
        binding.addFab.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AddItemActivity::class.java
                )
            )
        }

        GlobalScope.launch {
            val items = db.inventoryDao().getAll()

            withContext(Dispatchers.Main) {
                _adapter.data = items
            }
        }

        _adapter.updateListener = { item, updatedQuantity ->
            GlobalScope.launch {
                db.inventoryDao().update(
                    item.copy(quantity = updatedQuantity)
                )

                val items = db.inventoryDao().getAll()
                withContext(Dispatchers.Main) {
                    _adapter.data = items
                }
            }
        }

        _adapter.deleteListener = { item ->
            GlobalScope.launch {
                db.inventoryDao().delete(item)

                val items = db.inventoryDao().getAll()
                withContext(Dispatchers.Main) {
                    _adapter.data = items
                }
            }
        }
    }
}