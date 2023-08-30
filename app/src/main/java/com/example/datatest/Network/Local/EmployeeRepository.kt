package com.example.datatest.Network.Local

import androidx.lifecycle.LiveData

class EmployeeRepository(private val employeeDatabase: EmployeeDatabase) {

    val allNurse: LiveData<List<Employe>> =employeeDatabase.getEmployeeDao().getAllNotes()

  fun getRefreshData(){
      employeeDatabase.getEmployeeDao().getAllNotes()
  }

     suspend fun insert(employe:Employe){

        employeeDatabase.getEmployeeDao().insert(employe)
    }


    suspend fun delete(employe: Employe){

        employeeDatabase.getEmployeeDao().delete(employe)
    }

    suspend fun update(employe: Employe){

        employeeDatabase.getEmployeeDao().update(employe.id,employe.Email,employe.First_name,employe.Last_name,employe.avatar)


    }
}