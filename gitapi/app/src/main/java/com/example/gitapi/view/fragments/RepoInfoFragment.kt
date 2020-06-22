package com.example.gitapi.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gitapi.R
import com.example.gitapi.service.constants.RepoConstantants
import com.example.gitapi.service.model.RepoInfoModel
import com.example.gitapi.viewmodel.MainViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_repo_info.*
import java.text.SimpleDateFormat
import java.util.*


class RepoInfoFragment : Fragment() {

    private val mViewModel: MainViewModel by activityViewModels()
    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    private lateinit var extras: RepoInfoModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = Intent()


        extras.let { intent.extras?.get(RepoConstantants.BUNDLE.OBJECT_REPO) as RepoInfoModel }

        txt_desc.text = extras.desc


//        txt_title.text = extras.toString()
//
//
//        txt_star.text = extras.toString()
//
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
//        url_img.setOnClickListener {
//            val openURL = Intent(Intent.ACTION_VIEW)
//            openURL.data = Uri.parse(extras.toString())
//            startActivity(openURL)
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_info, container, false)
    }


}