package com.example.lesson6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson6.databinding.BigItemLayoutBinding
import com.example.lesson6.databinding.SmallItemLayoutBinding
import com.example.lesson6.entity.ItemEntity
import com.example.lesson6.viewHolder.BigItemViewHolder
import com.example.lesson6.viewHolder.SmallItemViewHolder

class RecyclerViewAdapter: ListAdapter<ItemEntity, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            SMALL_ITEM -> {
                val binding = SmallItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SmallItemViewHolder(binding)
            }
            BIG_ITEM -> {
                val binding = BigItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                BigItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder) {
            is SmallItemViewHolder -> bindSmallItem(holder, item.text)
            is BigItemViewHolder -> bindBigItem(holder, item.text)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position % 6) {
            0, 1, 2 -> SMALL_ITEM
            3, 4, 5 -> BIG_ITEM
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    private fun bindSmallItem(holder: SmallItemViewHolder, text: String) {
        with(holder.binding) {
            textViewItemText.text = text
        }
    }

    private fun bindBigItem(holder: BigItemViewHolder, text: String) {
        with(holder.binding) {
            textViewItemText.text = text
        }
    }

    companion object {
        private const val SMALL_ITEM = 0
        private const val BIG_ITEM = 1
    }

}