package com.example.gitapi.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Repositories")
class RepoModel {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("description")
    var desc: String = ""

    @SerializedName("stargazers_count")
    var stars: Int = 0

    @SerializedName("forks_count")
    var forks: Int = 0

    @SerializedName("open_issues_count")
    var issues: Int = 0

    @SerializedName("created_at")
    var date: String = ""

    @SerializedName("html_url")
    var url: String = ""

}