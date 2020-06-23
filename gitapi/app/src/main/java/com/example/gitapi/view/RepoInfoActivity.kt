package com.example.gitapi.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gitapi.R
import com.example.gitapi.service.constants.RepoConstantants
import com.example.gitapi.service.model.RepoInfoModel
import kotlinx.android.synthetic.main.activity_repo_info.*
import java.text.SimpleDateFormat
import java.util.*

class RepoInfoActivity : AppCompatActivity() {

    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_info)

        val bundle = intent.extras

        bundle?.let {
            val extras = it.getSerializable(RepoConstantants.BUNDLE.OBJECT_REPO) as RepoInfoModel
            txt_desc.text = extras.desc
            txt_title.text = extras.name
            txt_star.text = extras.stars.toString()
            txt_fork.text = extras.forks.toString()
            txt_issue.text = extras.issues.toString()

            val date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(extras.date)
            txt_data.text = mDateFormat.format(date)

            url_img.setOnClickListener {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(extras.url)
                startActivity(openURL)
            }
        }
    }
}