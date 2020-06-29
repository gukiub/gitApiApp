package com.example.gitapi.view.fragments

import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitapi.R
import com.example.gitapi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private val mViewModel: MainViewModel by activityViewModels()
    private val mainFragmentConst = "MainFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()



        view.findViewById<Button>(R.id.button_search).setOnClickListener {
            val search = editRepo.text.toString()
            mViewModel.search(search)
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d(mainFragmentConst, "onStart() : MainCriada")
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(mainFragmentConst, "onDestroy() : Main foi destruida")
    }

    private fun observe() {
        mViewModel.validation.observe(viewLifecycleOwner, Observer {
            if (it.success()) {
                val fragment = RepositoriesFragment()
                fragment.enterTransition = Slide(Gravity.RIGHT)
                fragment.exitTransition = Slide(Gravity.LEFT)
                val fragmentTransaction = activity?.let { it.supportFragmentManager.beginTransaction() }
                fragmentTransaction?.let { it.add(R.id.nav_host_fragment, fragment).addToBackStack(null).commit() }
            } else {
                Toast.makeText(context, it.failure(), Toast.LENGTH_SHORT).show()
            }
        })

    }
}