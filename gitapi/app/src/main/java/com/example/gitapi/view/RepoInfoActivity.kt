package com.example.gitapi.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gitapi.R
import com.example.gitapi.service.constants.UserConstants
import com.example.gitapi.viewmodel.RepoInfoViewModel
import kotlinx.android.synthetic.main.activity_repo.*
import kotlinx.android.synthetic.main.activity_repo_info.*
import kotlinx.android.synthetic.main.row_repo_list.*
import java.text.SimpleDateFormat
import java.util.*

class RepoInfoActivity : AppCompatActivity() {

    private lateinit var mViewModel: RepoInfoViewModel
    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_info)

        var extras = intent.extras!!.get(UserConstants.BUNDLE.DESC)
        txt_desc.text = extras.toString()

        extras = intent.extras!!.get(UserConstants.BUNDLE.REPONAME)
        txt_title.text = extras.toString()

        extras = intent.extras!!.get(UserConstants.BUNDLE.STAR)
        txt_star.text = extras.toString()


        extras = intent.extras!!.get(UserConstants.BUNDLE.FORK)
        txt_fork.text = extras.toString()

        extras = intent.extras!!.get(UserConstants.BUNDLE.ISSUE)
        txt_issue.text = extras.toString()

        extras = intent.extras!!.get(UserConstants.BUNDLE.DATE)
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(extras.toString())
        txt_data.text = mDateFormat.format(date)

        extras = intent.extras!!.get(UserConstants.BUNDLE.URL)
        url_img.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(extras.toString())
            startActivity(openURL)
        }


        mViewModel = ViewModelProvider(this).get(RepoInfoViewModel::class.java)

        observe()
    }


    private fun observe(){
        mViewModel.repo.observe(this, Observer {
            txt_desc.text = it.desc
        })
    }
}