package com.example.datatest.Network.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employe::class], version = 2, exportSchema = true)
abstract class EmployeeDatabase : RoomDatabase(){

    abstract fun getEmployeeDao() : EmployeeDao

    companion object{

        private var instance:EmployeeDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                EmployeeDatabase::class.java,
                "employee_table")
                .fallbackToDestructiveMigration()
                .build()

    }
}

