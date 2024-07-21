package com.example.fordelete2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fordelete2.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNotificationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toastString = intent.getStringExtra(BuildConfig.INTENT_KEY) ?: "Error, smth went wrong"
        binding.btnShowToast.setOnClickListener {
            setupShowToast(toastString)
        }

    }

    private fun setupShowToast(toastText: String) {
        Toast.makeText(
            this,
            toastText,
            Toast.LENGTH_SHORT
        ).show()
    }

}