package com.example.coffeeshop.activity

import android.content.Context
import android.os.Bundle
import android.view.RoundedCorner
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ActivityDetailBinding
import com.example.coffeeshop.model.CoffeeModel
import kotlin.math.round

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: CoffeeModel
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }
        setContentView(binding.root)

        /*        window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )*/

        context = this
        getBundles()

    }

    private fun getBundles() {
        item = intent.getParcelableExtra("object")!!
        binding.apply {
            name.text = item.title
            price.text = "$${item.price}"
            content.text = item.extra
            description.text = item.description
            Glide.with(context)
                .load(item.picUrl[0])
                .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
                .to(image)

            // Size button:
            sizeButton1.setOnClickListener {
                sizeButton1.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.orange_stroke_bg))
                sizeButton2.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.dark_grey_bg2))
                sizeButton3.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.dark_grey_bg2))
                sizeButton1.setTextColor(getResources().getColor(R.color.orange))
                sizeButton2.setTextColor(getResources().getColor(R.color.white))
                sizeButton3.setTextColor(getResources().getColor(R.color.white))
            }
            sizeButton2.setOnClickListener {
                sizeButton2.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.orange_stroke_bg))
                sizeButton1.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.dark_grey_bg2))
                sizeButton3.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.dark_grey_bg2))
                sizeButton2.setTextColor(getResources().getColor(R.color.orange))
                sizeButton1.setTextColor(getResources().getColor(R.color.white))
                sizeButton3.setTextColor(getResources().getColor(R.color.white))
            }
            sizeButton3.setOnClickListener {
                sizeButton3.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.orange_stroke_bg))
                sizeButton2.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.dark_grey_bg2))
                sizeButton1.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.dark_grey_bg2))
                sizeButton3.setTextColor(getResources().getColor(R.color.orange))
                sizeButton2.setTextColor(getResources().getColor(R.color.white))
                sizeButton1.setTextColor(getResources().getColor(R.color.white))
            }
        }
    }
}