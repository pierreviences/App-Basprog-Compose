package com.example.basprogapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.basprogapp.data.BasprogRepository
import com.example.basprogapp.ui.screen.detail.DetailViewModel
import com.example.basprogapp.ui.screen.favorite.FavoriteViewModel
import com.example.basprogapp.ui.screen.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BasprogRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}