package com.example.datatest.Network.Remote

import com.example.datatest.Network.LocalModel.EmployeeDataResponse
import retrofit2.Call

class EmployeeRepoRemote constructor(private val retrofitService: EmployeeService) {
     fun getCatagory(index:Int) = retrofitService.getEmploye(index)

}

