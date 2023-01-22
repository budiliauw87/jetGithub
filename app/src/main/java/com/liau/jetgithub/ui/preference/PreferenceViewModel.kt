package com.liau.jetgithub.ui.preference

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liau.jetgithub.core.data.GitRepository
import com.liau.jetgithub.core.model.ConfigApp
import com.liau.jetgithub.state.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class PreferenceViewModel(private val repository: GitRepository) : ViewModel() {
    val uiState: StateFlow<UiState<ConfigApp>> = repository.getPrefApp().map {
        UiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = UiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )

}