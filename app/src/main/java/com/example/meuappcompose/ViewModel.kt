package com.example.meuappcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PizzaViewModel: ViewModel() {
    var selectedSize by mutableStateOf("S")
    var currentPage by mutableStateOf(2)

    fun selectSize(size: String) {
        selectedSize = size
    }

    fun setPage(page: Int) {
        currentPage = page
    }
}