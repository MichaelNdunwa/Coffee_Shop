package com.example.coffeeshop.activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import com.example.coffeeshop.databinding.ActivityMainBinding
import eightbitlab.com.blurview.RenderScriptBlur

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBlurEffect()
        setVariable()
    }

    private fun setVariable() {
        binding.apply {
            iceDrinkButton.setOnClickListener { startListActivity(2, "Ice Drink") }
            hotDrinkButton.setOnClickListener { startListActivity(3, "Hot Drink") }
            hotCoffeeButton.setOnClickListener { startListActivity(1, "Hot Coffee") }
            iceCoffeeButton.setOnClickListener { startListActivity(4, "Ice Coffee") }
            brewingCoffeeButton.setOnClickListener { startListActivity(5, "Brewing Coffee") }
            shakeButton.setOnClickListener { startListActivity(6, "Shake") }
            restaurantButton.setOnClickListener { startListActivity(7, "Restaurant") }
            breakfastButton.setOnClickListener { startListActivity(8, "Breakfast") }
            cakeButton.setOnClickListener { startListActivity(9, "Cake") }
        }
    }

    private fun setBlurEffect() {
        val radius = 10f
        val decorView = this.window.decorView
        val rootView = decorView.findViewById<View>(R.id.content) as ViewGroup
        val windowBackground = decorView.background
        binding.blurView.setupWith(rootView, RenderScriptBlur(this))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)
        binding.blurView.setOutlineProvider(ViewOutlineProvider.BACKGROUND)
        binding.blurView.clipToOutline = true
    }

    private fun startListActivity(id: Int, title: String) {
        val intent = Intent(this, ListCategoryActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("title", title)
        startActivity(intent)
    }
}