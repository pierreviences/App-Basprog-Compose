package com.example.basprogapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basprogapp.data.BasprogRepository
import com.example.basprogapp.model.Basprog
import com.example.basprogapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: BasprogRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Basprog>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Basprog>>> get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun getBasprog() {
        viewModelScope.launch {
            repository.getBasprog()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
    fun searchBasprog(name: String) {
        _query.value = name
        viewModelScope.launch {
            repository.searchBasprog(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}