package com.example.gitapi.service.repository.local

import androidx.room.Dao
import androidx.room.Query
import com.example.gitapi.service.model.RepoModel

@Dao
interface RepositoriesDAO {
//    @Query("SELECT * FROM Repositories ORDER By `name`")
//    fun list(): ArrayList<RepoModel>
}