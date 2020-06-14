package com.example.gitapi.view.viewholder

import android.app.LauncherActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapi.R
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.service.listener.ReposListener
import com.example.gitapi.service.model.RepoModel
import kotlinx.android.synthetic.main.activity_repo_info.*

class ReposViewHolder(itemView: View, val listener: ReposListener) : RecyclerView.ViewHolder(itemView) {

    private val mTitle: TextView = itemView.findViewById(R.id.title_repo)
    private val mDesc: TextView = itemView.findViewById(R.id.description_repo)

    fun bindData(repo: RepoModel){
        this.mTitle.text = repo.name
        this.mDesc.text = repo.desc

        if (repo.desc == null){
            repo.desc = "nothing to show"
        }

        itemView.setOnClickListener{
            listener.onListClick(repo.id, repo.name, repo.desc, repo.stars, repo.forks, repo.issues, repo.url, repo.date)
        }
    }
}