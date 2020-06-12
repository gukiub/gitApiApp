package com.example.gitapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gitapi.R
import com.example.gitapi.service.model.UserModel
import com.example.gitapi.service.repository.remote.RetrofitClient
import com.example.gitapi.service.repository.remote.UserService
import com.example.gitapi.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            val remote = RetrofitClient.createService(UserService::class.java)
            val call: Call<UserModel> = remote.user(editRepo.text.toString())

            val response = call.enqueue(object : Callback<UserModel>{
                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    val s = t.message
                }

                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    val s = response.body()

                    if (s != null) {
                        Picasso.get().load(s.avatarUrl).into(imageView)
                    }
                }

            })
        }
    }

    private fun observe(){
        mViewModel.repo.observe(this, Observer {
            textView.text = it
        })
    }


}