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
import android.widget.Toast

class Mainregister : AppCompatActivity() {
    private lateinit var binding:ActivityMainregisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainregisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainRegisterLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnLogin.setOnClickListener(){
            val Username = binding.editUsername.text.toString()
            val Password = binding.editPassword.text.toString()
            val registeredUsername = intent.getStringExtra("USERNAME")
            val registeredPassword = intent.getStringExtra("PASSWORD")

            if (Username.isEmpty() || Password.isEmpty()) {
                Toast.makeText(this, "Username dan Password harus diisi", Toast.LENGTH_SHORT).show()
            } else if (Username == registeredUsername && Password == registeredPassword) {
                Toast.makeText(this, "Berhasil login", Toast.LENGTH_SHORT).show()

                // Lanjut ke halaman utama atau dashboard
                val intent = Intent(this, dashboard::class.java) // Ganti sesuai kebutuhanmu
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}