package com.example.gitapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gitapi.R
import com.example.gitapi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

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
        }
    }

    private fun observe(){
        mViewModel.validation.observe(this, Observer {
            if (it.success()) {
                startActivity(Intent(this, RepositoriesActivity::class.java))
            } else {
                Toast.makeText(this, it.failure(), Toast.LENGTH_SHORT).show()
            }
        })

    }


}