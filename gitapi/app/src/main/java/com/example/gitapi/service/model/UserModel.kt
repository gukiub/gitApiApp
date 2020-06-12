package com.example.gitapi.service.model

import com.google.gson.annotations.SerializedName

class UserModel {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("login")
    var login: String = ""

    @SerializedName("avatar_url")
    var avatarUrl = ""

    @SerializedName("name")
    var name = ""


}