package com.example.gitapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gitapi.R
import com.example.gitapi.service.constants.RepoConstantants
import com.example.gitapi.service.model.RepoInfoModel
import java.text.SimpleDateFormat
import java.util.*

class RepoInfoActivity : AppCompatActivity() {

    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_info)

        val intent = Intent()

        val extras = intent.getBundleExtra(RepoConstantants.BUNDLE.OBJECT_REPO) as RepoInfoModel


//        txt_desc.text = extras.desc


//        txt_title.text = extras.toString()
//
//
//        txt_star.text = extras.toString()
//
//
//        txt_fork.text = extras.toString()
//
//
//        txt_issue.text = extras.toString()
//
//
//        val date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(extras.toString())
//        txt_data.text = mDateFormat.format(date)
//
//
//        url_img.setOnClickListener{
//            val openURL = Intent(Intent.ACTION_VIEW)
//            openURL.data = Uri.parse(extras.toString())
//            startActivity(openURL)
//        }


//        mViewModel = ViewModelProvider(this).get(RepoInfoViewModel::class.java)

        observe()
    }


    private fun observe(){
//        mViewModel.repo.observe(this, Observer {
//            txt_desc.text = it.desc
//        })
    }
}