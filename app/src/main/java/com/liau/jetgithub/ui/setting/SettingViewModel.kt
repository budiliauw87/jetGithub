package com.liau.jetgithub.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liau.jetgithub.core.data.GitRepository
import com.liau.jetgithub.core.model.ConfigApp
import com.liau.jetgithub.state.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class SettingViewModel(private val repository: GitRepository) : ViewModel() {
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