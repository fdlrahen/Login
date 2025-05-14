package com.diva.helloword

import android.app.Activity
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

        binding.btnRegister.setOnClickListener(){
            val fullname = binding.textFullUsername.text.toString()
            val username = binding.TextUsername.text.toString()
            val password = binding.TextPassword.text.toString()
            val confirmPassword = binding.TextConfirmPassword.text.toString()

            if (fullname.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show()
            } else if (fullname != "admin") {
                Toast.makeText(this, "Fullname harus 'admin'", Toast.LENGTH_SHORT).show()
            } else if (username != "admin") {
                Toast.makeText(this, "Username harus 'admin'", Toast.LENGTH_SHORT).show()
            } else if (password != "admin" || confirmPassword != "admin") {
                Toast.makeText(this, "Password dan Konfirmasi harus 'admin'", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registrasi berhasil untuk $fullname ($username)", Toast.LENGTH_LONG).show()

            }
        }

        binding.btnLogin.setOnClickListener(){
            LoginDialog()
        }
    }
    private fun LoginDialog(){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.login, null)
    val editUsername = dialogView.findViewById<EditText>(R.id.editUsename)
        val editPassword = dialogView.findViewById<EditText>(R.id.editPassword)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Login")
            .setView(dialogView)
            .setPositiveButton("Login", null)
            .setNegativeButton("Cancel", null)
            .create()
        dialog.setOnShowListener{
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener{
                val username = editUsername.text.toString()
                val password = editPassword.text.toString()

                if(username == "admin" && password =="admin"){
                    Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
                }
            }
    }

        dialog.show()
    }
}