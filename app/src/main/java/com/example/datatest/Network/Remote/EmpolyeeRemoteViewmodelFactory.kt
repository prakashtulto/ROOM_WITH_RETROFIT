package com.example.datatest.Network.Remote

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EmpolyeeRemoteViewmodelFactory constructor(private val repository: EmployeeRepoRemote, var context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(EmployeeViewModelRemote::class.java)) {
            EmployeeViewModelRemote(this.repository, context) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}