package com.example.datatest.Network.Local

import androidx.lifecycle.LiveData
import androidx.room.*



@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(employe: Employe)

    @Delete
    suspend fun delete(employe: Employe)

    @Query("SELECT * from employee_table order by id ASC")
    fun getAllNotes():LiveData<List<Employe>>

    @Query("UPDATE employee_table SET email=:Email,first_name=:First_name,last_name=:Last_name,avatar=:avatar WHERE id=:id")
    suspend fun update(id:Int?,Email:String?,First_name:String?,Last_name:String?,avatar:String?)


}