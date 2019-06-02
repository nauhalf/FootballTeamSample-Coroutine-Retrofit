package com.dicoding.naufal.footballteamsample

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    private fun setUp(){
        val teamAdapter = TeamAdapter(this)
        recyclerview.apply {
            adapter = teamAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }

        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel.getData()

        mainViewModel.isLoading.observe(this, Observer { loading ->
            when(loading){
                true -> {
                    progress.visibility = View.VISIBLE
                }
                false -> {
                    progress.visibility = View.GONE
                }
            }
        })

        mainViewModel.teams.observe(this, Observer { teams ->
            teamAdapter.setTeams(teams)
        })


    }
}
