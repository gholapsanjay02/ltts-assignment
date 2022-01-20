package com.example.practice

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import api.API
import api.RetrofitAPI
import com.example.practice.databinding.ActivityMainBinding


// TODO: Display list of users with the user information mentioned in the assignment
// Note: A nice looking UI is appreciated but clean code is more important

class MainActivity : AppCompatActivity()
{

    lateinit var  viewModel: viewModel
    lateinit var  binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupObserver()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.retryButton.setOnClickListener{
            viewModel.fetchData()
            setupObserver()
        }

    }

    private fun setupObserver() {
        viewModel.apiResult.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = Adapter(it.data!!)
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.retryButton.visibility = View.INVISIBLE

                }
                Status.LOADING -> {
                    binding.recyclerView.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.retryButton.visibility = View.INVISIBLE

                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.retryButton.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun setupViewModel() {
        val api = API()
        val repo = RetrofitAPI(api)
        val factory = ModelFactory(repo)
        viewModel = ViewModelProvider(this,factory).get(com.example.practice.viewModel::class.java)
    }

}