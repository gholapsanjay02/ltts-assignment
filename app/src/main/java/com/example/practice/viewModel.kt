package com.example.practice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import api.API
import api.RetrofitAPI
import emp.EmployeeDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class viewModel (private val repository: RetrofitAPI): ViewModel()
{
    val apiResult = MutableLiveData<Resource<EmployeeDetails>>()

    init {
        fetchData()
    }

    fun fetchData() {
        CoroutineScope(Dispatchers.Default).launch {
            apiResult.postValue(Resource.loading(null))
            try {
                val datFromApideferred =  repository.getRepositoryResult()
                apiResult.postValue(Resource.success(datFromApideferred.body()))


            }catch (e: Exception)
            {
                apiResult.postValue(Resource.error("error while loading data ",null))
            }
        }
    }
}