package com.example.gitapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapi.R
import com.example.gitapi.service.listener.ReposListener
import com.example.gitapi.service.model.RepoModel
import com.example.gitapi.view.viewholder.ReposViewHolder

class ReposAdapter : RecyclerView.Adapter<ReposViewHolder>(){

    private var mList: ArrayList<RepoModel> = arrayListOf()
    private lateinit var mListener: ReposListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder{
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_repo_list, parent, false)
        return ReposViewHolder(item, mListener)
    }

    fun attachListener(listener: ReposListener){
        mListener = listener
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bindData(mList[position])
    }


    fun updateList(mList: ArrayList<RepoModel>){
        val size = this.mList.size
        this.mList.addAll(mList)
        val sizeNew = this.mList.size
        notifyItemRangeChanged(size, sizeNew)
    }

}