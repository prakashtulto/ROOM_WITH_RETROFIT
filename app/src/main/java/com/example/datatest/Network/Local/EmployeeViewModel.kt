package com.example.datatest.Network.Local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datatest.Network.Remote.EmployeeRepoRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback


class EmployeeViewModel(   private val repository: EmployeeRepository,
                           ): ViewModel(){


    var allnurse: LiveData<List<Employe>> = repository.allNurse

    fun getRefreshData(){
        repository.getRefreshData()
    }

    fun deleteEmploye(employe:Employe) =viewModelScope.launch(Dispatchers.IO ) {

        repository.delete(employe)
    }

    fun insertEmploye(employe: Employe)=viewModelScope.launch(Dispatchers.IO) {

        repository.insert(employe)
    }

    fun updateEmploye(employe: Employe)=viewModelScope.launch(Dispatchers.IO) {

        repository.update(employe)
    }









}