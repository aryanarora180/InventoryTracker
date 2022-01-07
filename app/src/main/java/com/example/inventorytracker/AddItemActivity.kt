package com.example.inventorytracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.inventorytracker.databinding.ActivityAddItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            InventoryDatabase::class.java, "inventory"
        ).build()

        binding.saveButton.setOnClickListener {
            val name = binding.nameText.text.toString()
            val category = binding.categoryText.text.toString()
            val quantity = binding.quantityText.text.toString().toInt()

            GlobalScope.launch {
                db.inventoryDao().insert(
                    InventoryItem(
                        0, name, category, quantity
                    )
                )
            }

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}