package com.example.integrationofapi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.integrationofapi.adapter.NewsAdapter
import com.example.integrationofapi.databinding.ActivityMainBinding
import com.example.integrationofapi.newsViewModel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.apply {
            binding.apply {
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                newsList.observe(this@MainActivity) { articles ->
                    recyclerView.adapter = NewsAdapter(articles)
                }
            }
            fetchHeadlines("us", "b1eb506867f94124b7faf04d345cc824")
        }
    }
}

