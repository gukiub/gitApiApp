package com.example.gitapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapi.R
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.service.listener.ApiListener
import com.example.gitapi.service.listener.ReposListener
import com.example.gitapi.service.model.UserModel
import com.example.gitapi.service.repository.local.SecurityPreferences
import com.example.gitapi.view.adapter.ReposAdapter
import com.example.gitapi.viewmodel.RepositoriesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_repo.*

class RepositoriesActivity : AppCompatActivity() {

    private lateinit var mViewModel: RepositoriesViewModel
    private lateinit var mListener: ReposListener
    private var mAdapter = ReposAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        mViewModel = ViewModelProvider(this).get(RepositoriesViewModel::class.java)
        val recycler = findViewById<RecyclerView>(R.id.repoRecycler)
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        recycler.adapter = mAdapter

        observe()

        mListener = object : ReposListener{
            override fun onListClick(id: Int, name: String, desc: String, stars: Int, forks: Int, issues: Int, url: String, date: String){
                val intent = Intent(applicationContext, RepoInfoActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(UserConstants.BUNDLE.REPOID, id)
                bundle.putString(UserConstants.BUNDLE.REPONAME, name)
                bundle.putString(UserConstants.BUNDLE.DESC, desc)
                bundle.putInt(UserConstants.BUNDLE.STAR, stars)
                bundle.putInt(UserConstants.BUNDLE.FORK, forks)
                bundle.putInt(UserConstants.BUNDLE.ISSUE, issues)
                bundle.putString(UserConstants.BUNDLE.URL, url)
                bundle.putString(UserConstants.BUNDLE.DATE, date)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.list()
    }

    override fun onDestroy() {
        super.onDestroy()
//        mViewModel.limparSessao()
    }

    private fun observe(){
        mViewModel.repos.observe(this, Observer {
            if (it.count() > 0){
                mAdapter.updateList(it)
            }
        })

        mViewModel.image.observe(this, Observer {
            Picasso.get().load(it).into(imgPerfil)
        })

        mViewModel.name.observe(this, Observer {
            txtNome.text = it
        })
    }
}