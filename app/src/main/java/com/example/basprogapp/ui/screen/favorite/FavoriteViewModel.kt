package com.example.basprogapp.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basprogapp.data.BasprogRepository
import com.example.basprogapp.model.Basprog
import com.example.basprogapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: BasprogRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Basprog>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Basprog>>> get() = _uiState

    fun getFavoriteBasprog() {
        viewModelScope.launch {
            repository.getFavoriteBasprog()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}