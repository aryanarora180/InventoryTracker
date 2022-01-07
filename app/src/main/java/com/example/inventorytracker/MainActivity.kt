package com.example.inventorytracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.inventorytracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val _adapter = InventoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inventoryRecycler.adapter = _adapter
        binding.addFab.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AddItemActivity::class.java
                )
            )
        }

        // TODO: Get items from database and update

        _adapter.updateListener = { item, updatedQuantity ->
            // TODO: Update in database
        }

        _adapter.deleteListener = {
            // TODO: Delete in database and reset items
        }
    }
}