package com.diva.userapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _averagePrice = MutableLiveData<String>()
    val averagePrice: LiveData<String> = _averagePrice

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getUsers()
                _users.value = response.users
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load users: ${e.message}"
                e.printStackTrace()
            }
        }
    }

    fun fetchAverageProductPrice() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProducts()
                val prices = response.products.map { it.price }
                val average = prices.average()
                _averagePrice.value = formatToRupiah(average)
            } catch (e: Exception) {
                _averagePrice.value = "Gagal memuat harga: ${e.message}"
            }
        }
    }
    private fun formatToRupiah(value: Double): String {
        val symbols = java.text.DecimalFormatSymbols().apply {
            groupingSeparator = '.'
            decimalSeparator = ','
        }
        val formatter = java.text.DecimalFormat("#,###.00", symbols)
        return "Rp ${formatter.format(value)}"
    }

}