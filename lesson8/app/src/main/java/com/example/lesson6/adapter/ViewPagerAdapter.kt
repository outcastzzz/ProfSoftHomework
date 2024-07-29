package com.example.lesson6.adapter

    import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson6.databinding.ViewPagerItemLayoutBinding
import com.example.lesson6.viewHolder.ViewPagerViewHolder

class ViewPagerAdapter(private val items: List<String>) : RecyclerView.Adapter<ViewPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ViewPagerItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val item = items[position]
        holder.binding.textViewItemText.text = item
    }

    override fun getItemCount() = items.size
}