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

class MainActivity : AppCompatActivity() {
  //  private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
          val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
          v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
          insets
        }
        val EditTextUsername = findViewById< EditText >(R.id.TextUsername)
        val EditTextPassword  = findViewById< EditText >(R.id.TextPassword)
        val EditTextConfirmPassword  = findViewById< EditText >(R.id.TextConfirmPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener(){
            val username = EditTextUsername.text.toString()
            val password = EditTextPassword.text.toString()
            val confirmPassword = EditTextConfirmPassword.text.toString()

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Password dan konfirmasi tidak cocok", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registrasi berhasil untuk $username", Toast.LENGTH_SHORT)
                    .show()
            }
         }

        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener(){
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