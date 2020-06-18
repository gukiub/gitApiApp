package com.example.gitapi.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gitapi.service.model.RepoModel

@Database(entities = [RepoModel::class], version = 1)
abstract class RepositoriesDatabase : RoomDatabase(){

    abstract fun repoDAO(): RepositoriesDAO

    companion object{
        private lateinit var INSTANCE: RepositoriesDatabase

        fun getDatabase(context: Context): RepositoriesDatabase{
            if(!Companion::INSTANCE.isInitialized){
                synchronized(RepositoriesDatabase::class){
                    INSTANCE = Room.databaseBuilder(context, RepositoriesDatabase::class.java, "repoDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}