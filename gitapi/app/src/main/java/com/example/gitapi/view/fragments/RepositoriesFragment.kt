package com.example.gitapi.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapi.R
import com.example.gitapi.service.constants.RepoConstantants
import com.example.gitapi.service.listener.ReposListener
import com.example.gitapi.service.model.RepoInfoModel
import com.example.gitapi.view.RepoInfoActivity
import com.example.gitapi.view.adapter.ReposAdapter
import com.example.gitapi.viewmodel.MainViewModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_repo.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RepositoriesFragment : Fragment() {
    private val mViewModel: MainViewModel by activityViewModels()
    private lateinit var mListener: ReposListener
    private var mAdapter = ReposAdapter()
    private var page: Int = 1

    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        observe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.repoRecycler)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        mListener = object : ReposListener{
            override fun onListClick(id: Int, name: String, desc: String, stars: Int, forks: Int, issues: Int, url: String, date: String) {
                val intent = Intent(context, RepoInfoActivity::class.java)
//                val bundle = Bundle()
                val repo = RepoInfoModel(id, name, desc, stars, forks, issues, url, date)
//                bundle.putSerializable(RepoConstantants.BUNDLE.OBJECT_REPO, repo)

                intent.putExtra(RepoConstantants.BUNDLE.OBJECT_REPO, repo)
                if(intent.hasExtra(RepoConstantants.BUNDLE.OBJECT_REPO)) {
                    startActivity(intent)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.list(page)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }


    private fun observe(){
        mViewModel.list.observe(this, Observer {
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