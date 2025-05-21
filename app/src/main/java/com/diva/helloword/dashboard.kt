package com.diva.helloword

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.diva.helloword.databinding.ActivityDashboardBinding

class dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainDashboardLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Ambil username dari intent
        val username = intent.getStringExtra("USERNAME")
        binding.textWelcome.text = "Selamat datang, $username!"

        // Tombol logout kembali ke login
        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, Mainregister::class.java)
            startActivity(intent)
            finish()
        }
    }
}