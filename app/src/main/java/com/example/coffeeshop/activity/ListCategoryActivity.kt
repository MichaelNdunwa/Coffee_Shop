package com.example.coffeeshop.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshop.R
import com.example.coffeeshop.adapter.ListCoffeeAdapter
import com.example.coffeeshop.databinding.ActivityCategoryListBinding
import com.example.coffeeshop.model.CoffeeModel
import com.example.coffeeshop.view_model.MainViewModel

class ListCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryListBinding
    private val viewModel = MainViewModel()
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryListBinding.inflate(layoutInflater)
        context = this@ListCategoryActivity
        setContentView(binding.root)

        setUpTitle()
        fetchCoffeeList()
    }

    private fun setUpTitle() {
        // menu button:
        binding.menuButton.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }

        binding.categoryTextView.text = intent.getStringExtra("title")
        val imageId = intent.getIntExtra("id", 0)
        when (imageId) {
            1 -> binding.categoryImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_1))
            2 -> binding.categoryImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_2))
            3 -> binding.categoryImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_3))
            4 -> binding.categoryImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_4))
            5 -> binding.categoryImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_5))
            6 -> binding.categoryImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_6))
            7 -> binding.categoryImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_7))
            8 -> binding.categoryImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_8))
            9 -> binding.categoryImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_9))
        }

    }

    private fun fetchCoffeeList() {
        binding.progressBar.visibility = View.VISIBLE
        val id = intent.getIntExtra("id", 0)
        viewModel.loadCoffeeCategory(id).observe(this@ListCategoryActivity, Observer { coffeeItem ->
            Log.d("wasCoffeeDataFetched", "Coffee item size: ${coffeeItem.size}")
            Log.d("wasCoffeeDataFetched", "Coffee item data: $coffeeItem")
            Log.d("wasCoffeeDataFetched", "Is coffee item empty: ${coffeeItem.isEmpty()}")
            if (coffeeItem.isNotEmpty()) {
                binding.coffeeList.layoutManager = LinearLayoutManager(this@ListCategoryActivity, LinearLayoutManager.VERTICAL, false)
                binding.coffeeList.adapter = ListCoffeeAdapter(coffeeItem)
                binding.emptyListText.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            } else {
                binding.emptyListText.visibility = View.VISIBLE
            }
        })

    }

}