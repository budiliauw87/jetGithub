package com.liau.jetgithub.ui.preference

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.liau.jetgithub.core.data.GitRepository

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class PreferenceViewModel(private val repository: GitRepository) : ViewModel() {
    fun getDarkTheme(): LiveData<String> = repository.getDarkMode()
    fun getLanguage(): LiveData<String> = repository.getLanguageApp()
}