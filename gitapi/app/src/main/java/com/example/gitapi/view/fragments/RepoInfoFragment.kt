package com.example.gitapi.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.gitapi.R
import com.example.gitapi.service.constants.RepoConstantants
import com.example.gitapi.service.model.RepoInfoModel
import com.example.gitapi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_repo_info.*
import java.text.SimpleDateFormat
import java.util.*

private val mDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

class RepoInfoFragment : Fragment() {
    private val mViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val extras =
                it?.getSerializable(RepoConstantants.BUNDLE.OBJECT_REPO) as RepoInfoModel

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_info, container, false)
    }

}