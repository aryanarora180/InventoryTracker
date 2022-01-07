package com.example.inventorytracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorytracker.databinding.RowInventoryItemBinding

class InventoryAdapter : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    var data = listOf<InventoryItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var updateListener: (InventoryItem, Int) -> Unit

    lateinit var deleteListener : (InventoryItem) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowInventoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        with(holder.binding) {
            nameText.text = item.name
            categoryText.text = item.category
            quantityText.text = item.quantity.toString()

            decrementButton.setOnClickListener { updateListener(item, item.quantity - 1) }
            incrementButton.setOnClickListener { updateListener(item, item.quantity + 1) }

            root.setOnLongClickListener {
                deleteListener(item)
                true
            }
        }
    }

    override fun getItemCount() = data.size

    class ViewHolder(val binding: RowInventoryItemBinding) : RecyclerView.ViewHolder(binding.root)
}