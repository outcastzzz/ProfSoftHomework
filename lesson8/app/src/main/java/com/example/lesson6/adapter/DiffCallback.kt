package com.example.lesson6.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.lesson6.entity.ItemEntity

object DiffCallback: DiffUtil.ItemCallback<ItemEntity>() {

    override fun areItemsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean {
        return oldItem == newItem
    }
}