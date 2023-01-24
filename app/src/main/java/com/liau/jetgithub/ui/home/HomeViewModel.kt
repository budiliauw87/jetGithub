package com.liau.jetgithub.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liau.jetgithub.core.data.GitRepository
import com.liau.jetgithub.core.data.network.response.Response
import com.liau.jetgithub.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * Created by Budiman on 24/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class HomeViewModel(private val repository: GitRepository) : ViewModel() {
    private val _uiStateUser: MutableStateFlow<UiState<Response>> = MutableStateFlow(UiState.Loading)
    val uiStateUser: StateFlow<UiState<Response>>
        get() = _uiStateUser

    fun getUser() {
        viewModelScope.launch {
            _uiStateUser.value = UiState.Loading
            repository.getUser().catch {
                _uiStateUser.value = UiState.Error(it.message.toString())
            }.collect {
                _uiStateUser.value = UiState.Success(it)
            }
        }
    }
}