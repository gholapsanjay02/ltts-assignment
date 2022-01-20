package com.example.practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import api.RetrofitAPI

class ModelFactory(private val repo:RetrofitAPI):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel::class.java)) {
            return viewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}