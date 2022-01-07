package com.example.inventorytracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.inventorytracker.databinding.ActivityAddItemBinding

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.nameText.text.toString()
            val category = binding.categoryText.text.toString()
            val quantity = binding.quantityText.text.toString().toInt()

            // TODO: Save item in database

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}