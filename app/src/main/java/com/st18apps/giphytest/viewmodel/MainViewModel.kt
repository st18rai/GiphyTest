package com.st18apps.giphytest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.st18apps.giphytest.data.Result
import com.st18apps.giphytest.data.repository.Repository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: Repository) : ViewModel() {

    fun search(query: String) = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            emit(Result.success(data = repository.search(query)))
        } catch (exception: Exception) {
            emit(Result.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}