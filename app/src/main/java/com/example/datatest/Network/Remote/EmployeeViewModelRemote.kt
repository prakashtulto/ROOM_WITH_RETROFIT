package com.example.datatest.Network.Remote

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datatest.Network.LocalModel.EmployeeDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class EmployeeViewModelRemote constructor(private val repository: EmployeeRepoRemote, var context: Context) :
    ViewModel()  {

    val empDataLiveData = MutableLiveData<EmployeeDataResponse>()
    val errorMessage = MutableLiveData<String>()

      fun getRemoteData() {
        val response = repository.getCatagory(1)
        response.enqueue(object : Callback<EmployeeDataResponse> {
            override fun onResponse(
                call: Call<EmployeeDataResponse>,
                response: Response<EmployeeDataResponse>
            ) {

                empDataLiveData.postValue(response.body())

            }
            override fun onFailure(call: Call<EmployeeDataResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })




    }



}