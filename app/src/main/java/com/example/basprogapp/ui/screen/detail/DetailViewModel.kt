package com.example.basprogapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basprogapp.data.BasprogRepository
import com.example.basprogapp.model.Basprog
import com.example.basprogapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: BasprogRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Basprog>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Basprog>> get() = _uiState
    fun getBasprogById(id: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getBasprogById(id))
        }
    }
    fun addToFavorite(id: String, isFavorite:Boolean) =
        viewModelScope.launch {
            repository.updateFavoriteBasprog(id, isFavorite)
                .collect{
                    if (it) getBasprogById(id)
                }
        }
}