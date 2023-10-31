package com.example.basprogapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.basprogapp.data.BasprogRepository
import com.example.basprogapp.ui.screen.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BasprogRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}