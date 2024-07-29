package com.example.lesson6

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.lesson6.adapter.RecyclerViewAdapter
import com.example.lesson6.adapter.ViewPagerAdapter
import com.example.lesson6.databinding.ActivityMainBinding
import com.example.lesson6.entity.ItemEntity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var dotsLayout: LinearLayout
    private lateinit var dots: Array<ImageView?>
    private var currentItemLastNum = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setupRecyclerView()
        setupViewPager()
    }

    private fun setupRecyclerView() {
        val adapter = RecyclerViewAdapter()
        val rvList = listOf(
            ItemEntity("item 1"),
            ItemEntity("item 2"),
            ItemEntity("item 3"),
            ItemEntity("item 4"),
            ItemEntity("item 5"),
            ItemEntity("item 6"),
        )
        binding.recyclerView.adapter = adapter
        adapter.submitList(rvList)
        binding.textViewAddItem.setOnClickListener {
            addItem(adapter)
        }
    }

    private fun setupViewPager() {
        val viewPager: ViewPager2 = binding.viewPager2
        dotsLayout = binding.dotsLayout

        val items = listOf("item 1", "item 2", "item 3", "item 4", "item 5")
        viewPager.adapter = ViewPagerAdapter(items)

        setupDots(items.size)
        selectDot(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectDot(position)
            }
        })
    }

    private fun addItem(adapter: RecyclerViewAdapter) {
        val nextItemText = "item ${currentItemLastNum + 1}"
        currentItemLastNum++
        val newList = adapter.currentList.toMutableList()
        newList.add(ItemEntity(nextItemText))
        adapter.submitList(newList)
    }

    private fun setupDots(count: Int) {
        dots = arrayOfNulls(count)
        for (i in 0 until count) {
            dots[i] = ImageView(this).apply {
                setImageDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.non_active_dot))
            }
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                setMargins(8, 0, 8, 0)
            }
            dotsLayout.addView(dots[i], params)
        }
    }

    private fun selectDot(position: Int) {
        for (i in dots.indices) {
            val drawableId = if (i == position) R.drawable.active_dot else R.drawable.non_active_dot
            dots[i]?.setImageDrawable(ContextCompat.getDrawable(this, drawableId))
        }
    }

}