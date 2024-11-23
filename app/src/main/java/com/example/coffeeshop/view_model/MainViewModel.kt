package com.example.coffeeshop.view_model

import androidx.lifecycle.ViewModel
import com.example.coffeeshop.repository.MainRepository

class MainViewModel() : ViewModel()  {
    private val repository = MainRepository()
    fun loadCoffeeCategory(id: Int) = repository.loadFiltered(id)

}