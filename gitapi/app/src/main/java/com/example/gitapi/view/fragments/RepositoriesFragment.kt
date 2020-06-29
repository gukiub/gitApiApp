package com.example.gitapi.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapi.R
import com.example.gitapi.service.constants.RepoConstantants
import com.example.gitapi.service.listener.ReposListener
import com.example.gitapi.service.model.RepoInfoModel
import com.example.gitapi.view.adapter.ReposAdapter
import com.example.gitapi.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_repositories.*
import kotlinx.android.synthetic.main.fragment_repositories.toolbar

class RepositoriesFragment : Fragment() {
    private val mViewModel: MainViewModel by activityViewModels()
    private lateinit var mListener: ReposListener
    private var mAdapter = ReposAdapter()
    private var page: Int = 1
    private val repoFragment = "RepositoriesFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.repoRecycler)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter


        mListener = object : ReposListener {
            override fun onListClick(
                id: Int,
                name: String,
                desc: String,
                stars: Int,
                forks: Int,
                issues: Int,
                url: String,
                date: String
            ) {
//                val intent = Intent(context, RepoInfoActivity::class.java)

//
//                intent.putExtras(bundle)
//                startActivity(intent)

                val fragment = RepoInfoFragment()
                val bundle = Bundle()
                val repo = RepoInfoModel(id, name, desc, stars, forks, issues, url, date)
                bundle.putSerializable(RepoConstantants.BUNDLE.OBJECT_REPO, repo)
                fragment.arguments = bundle
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.add(R.id.nav_host_fragment, fragment)?.addToBackStack(null)?.commit()

            }
        }

        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar as Toolbar)
        val customToolbar = (activity as AppCompatActivity).supportActionBar

        customToolbar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setHomeButtonEnabled(true)
            it.title = "Repositórios"
        }

        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.list.value?.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.list(page)
    }


    private fun observe() {
        mViewModel.list.observe(this, Observer {
            if (it.count() > 0) {
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