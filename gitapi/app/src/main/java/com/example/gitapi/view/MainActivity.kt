package com.example.gitapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gitapi.R
import com.example.gitapi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        observe()

        button_search.setOnClickListener{
            val search = editRepo.text.toString()
            mViewModel.search(search)
            if(editRepo.text.toString() != "") {
                startActivity(Intent(this, RepoActivity::class.java))
            } else {
                Toast.makeText(this, getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observe(){
        mViewModel.repo.observe(this, Observer {

        })
    }

}