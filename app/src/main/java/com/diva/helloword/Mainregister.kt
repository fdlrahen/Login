package com.diva.helloword

import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.view.LayoutInflater
import androidx.core.view.WindowInsetsCompat
import com.diva.helloword.databinding.ActivityMainregisterBinding
import android.content.Intent

class Mainregister : AppCompatActivity() {
    private lateinit var binding:ActivityMainregisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainregisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnregister.setOnClickListener(){
            val Username = binding.textFullName.text.toString()
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("USERNAME", Username)
            }
            startActivity(intent)
            finish()
        }
    }
}