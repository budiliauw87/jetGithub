package com.liau.jetgithub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liau.jetgithub.core.data.GitRepository
import com.liau.jetgithub.core.data.network.response.Response
import com.liau.jetgithub.core.model.ConfigApp
import com.liau.jetgithub.state.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


/**
 * Created by Budiliauw87 on 2023-01-24.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
class MainViewModel(private val repository: GitRepository) : ViewModel() {

    private val _uiStateUser: MutableStateFlow<UiState<Response>> =
        MutableStateFlow(UiState.Loading)
    val uiStateUser: StateFlow<UiState<Response>>
        get() = _uiStateUser

    val uiState: StateFlow<UiState<ConfigApp>> = repository.getPrefApp().map {
        UiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = UiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    fun saveLanguage(selectedLanguage: String) {
        viewModelScope.launch {
            repository.saveLanguage(selectedLanguage)
        }
    }

    fun saveDarkMode(newValue: Boolean) {
        viewModelScope.launch {
            repository.saveDarkTheme(newValue)
        }
    }


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