package com.example.datatest.Network.Remote

import com.example.datatest.Network.LocalModel.EmployeeDataResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface EmployeeService {



    @GET("users")
    fun getEmploye(@Query("page") page: Int) :Call<EmployeeDataResponse>

    companion object {
        var retrofitService: EmployeeService? = null
        fun getInstance(): EmployeeService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
//
                    .build()
                retrofitService = retrofit.create(EmployeeService::class.java)
            }
            return retrofitService!!
        }
    }
}