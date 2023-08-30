package com.example.datatest.Network.Local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "employee_table")
data class Employe(

    @PrimaryKey val id:Int?,
    @ColumnInfo(name = "email")val Email:String?,
    @ColumnInfo(name="first_name") val First_name:String?,
    @ColumnInfo(name = "last_name")val Last_name:String?,
    @ColumnInfo(name = "avatar")val avatar:String?,



    )
