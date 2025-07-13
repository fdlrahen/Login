package com.diva.userapi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.diva.userapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MyViewModel
    private val allUsers = mutableListOf<User>()
    private var currentIndex = 0

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

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        // Observasi data user
        viewModel.users.observe(this) { users ->
            allUsers.clear()
            allUsers.addAll(users)
            currentIndex = 0
            showUser(currentIndex)
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            binding.txtFirstName.text = errorMessage
            binding.txtLastName.text = ""
            binding.txtUniversity.text = ""
            binding.txtDetailsData.text = ""
        }

        viewModel.averagePrice.observe(this) { formattedPrice ->
            binding.txtAveragePrice.text = "Rata-rata harga: $formattedPrice"
        }

        binding.btnGetUsers.setOnClickListener {
            viewModel.fetchUsers()
        }

        binding.previous.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                showUser(currentIndex)
            } else {
                Toast.makeText(this, "Kamu sudah di user pertama", Toast.LENGTH_SHORT).show()
            }
        }

        binding.next.setOnClickListener {
            if (currentIndex < allUsers.size - 1) {
                currentIndex++
                showUser(currentIndex)
            } else {
                Toast.makeText(this, "Kamu sudah di user terakhir", Toast.LENGTH_SHORT).show()
            }
        }

        binding.getProduct.setOnClickListener {
            viewModel.fetchAverageProductPrice()
        }
    }

    private fun showUser(index: Int) {
        val user = allUsers[index]
        binding.txtFirstName.text = user.firstName
        binding.txtLastName.text = user.lastName
        binding.txtUniversity.text = user.university
        binding.txtDetailsData.text = "ID: ${user.id}"
    }
}