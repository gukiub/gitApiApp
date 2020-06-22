package com.example.gitapi.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapi.R
import com.example.gitapi.service.constants.RepoConstantants
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.service.listener.ReposListener
import com.example.gitapi.service.model.RepoInfoModel
import com.example.gitapi.view.adapter.ReposAdapter
import com.google.gson.Gson


class RepositoriesActivity : AppCompatActivity() {
//    private lateinit var mViewModel: RepositoriesViewModel
    private lateinit var mListener: ReposListener
    private var mAdapter = ReposAdapter()
    private var page: Int = 1

    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

//        mViewModel = ViewModelProvider(this).get(RepositoriesViewModel::class.java)
        val recycler = findViewById<RecyclerView>(R.id.repoRecycler)
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        recycler.adapter = mAdapter


//        observe()

        mListener = object : ReposListener{
            override fun onListClick(id: Int, name: String, desc: String, stars: Int, forks: Int, issues: Int, url: String, date: String){

                val intent = Intent(applicationContext, RepoInfoActivity::class.java)

                val repo = RepoInfoModel(id, name, desc, stars, forks, issues, url, date)
                val bundle = Bundle()

                bundle.putSerializable(RepoConstantants.BUNDLE.OBJECT_REPO, Gson().toJson(repo))

                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
//        mViewModel.list(page)
    }



}