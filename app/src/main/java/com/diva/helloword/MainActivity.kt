package com.diva.helloword

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.diva.helloword.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRegister.setOnClickListener() {
            val fullname = binding.textFullUsername.text.toString()
            val username = binding.TextUsername.text.toString()
            val password = binding.TextPassword.text.toString()
            val confirmPassword = binding.TextConfirmPassword.text.toString()

            if (fullname.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show()
                } else if (password != confirmPassword) {
                    Toast.makeText(this, "Konfirmasi password tidak cocok", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    // Kirim data ke login
                    val intent = Intent(this, Mainregister::class.java).apply {
                        putExtra("USERNAME", username)
                        putExtra("PASSWORD", password)
                    }
                    startActivity(intent)
                    finish()
                }
            }
            binding.btnlogin.setOnClickListener {
                // Langsung pindah ke login (tanpa register)
                val intent = Intent(this, Mainregister::class.java)
                startActivity(intent)
        }
        }
    }
