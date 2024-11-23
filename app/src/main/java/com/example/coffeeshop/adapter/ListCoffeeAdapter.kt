package com.example.coffeeshop.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeshop.activity.DetailActivity
import com.example.coffeeshop.databinding.CoffeeViewHolderBinding
import com.example.coffeeshop.model.CoffeeModel

class ListCoffeeAdapter(val items: MutableList<CoffeeModel>) :
    RecyclerView.Adapter<ListCoffeeAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(val binding: CoffeeViewHolderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): ListCoffeeAdapter.ViewHolder {
        context = parent.context
        val binding = CoffeeViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListCoffeeAdapter.ViewHolder, position: Int) {
        holder.binding.apply {
            name.text = items[position].title
            content.text = items[position].extra
            price.text = "$ ${items[position].price}"
            Glide.with(holder.itemView.context).load(items[position].picUrl[0]).into(image)
            root.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailActivity::class.java)
                intent.putExtra("object", items[position])
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = items.size

}