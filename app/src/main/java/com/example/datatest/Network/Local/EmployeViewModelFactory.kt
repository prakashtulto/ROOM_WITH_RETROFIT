package com.example.datatest.Network.Local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EmployeViewModelFactory(
    private val repository: EmployeeRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EmployeeViewModel(repository) as T

    }

}