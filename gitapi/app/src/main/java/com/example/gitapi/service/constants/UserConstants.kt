package com.example.gitapi.service.constants

class UserConstants private constructor(){

    object SHARED{
        const val SEARCH_KEY = "userNickName"
        const val IMG_AVATAR = "imgAvatar"
        const val NAME = "name"
    }

    object HTTP {
        const val FORBIDDEN = 403
        const val SUCCESS = 200
        const val NOTFOUND = 404
    }

    object BUNDLE {
        const val REPOID = "repoId"
        const val REPONAME = "repoName"
        const val DESC = "description"
        const val STAR = "star"
        const val FORK = "fork"
        const val ISSUE = "issue"
        const val URL = "url"
        const val DATE = "date"
    }
}