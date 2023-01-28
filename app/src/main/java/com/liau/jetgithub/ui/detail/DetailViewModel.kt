package com.liau.jetgithub.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.liau.jetgithub.core.data.GitRepository


/**
 * Created by Budiliauw87 on 2023-01-28.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
class DetailViewModel(private val repository: GitRepository) : ViewModel() {
    val detailPaging = repository.detailItems.cachedIn(viewModelScope)
    fun setPaging(method: Int, login: String) {
        repository.loginUser.value = login
        repository.methodQuery.value = method
    }
}