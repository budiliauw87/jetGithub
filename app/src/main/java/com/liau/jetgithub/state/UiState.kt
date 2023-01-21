package com.liau.jetgithub.state

/**
 * Created by Budiman on 20/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
sealed class UiState<out T : Any?> {
    object Loading : UiState<Nothing>()
    data class Success<out T: Any>(val data: T) : UiState<T>()
    data class Error(val errorMessage: String) : UiState<Nothing>()
}