package com.liau.jetgithub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.liau.jetgithub.core.data.GitRepository
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
    val querySearchFlow: MutableStateFlow<String> = repository.querySearchFlow
    val userPaging = repository.items.cachedIn(viewModelScope)
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
}