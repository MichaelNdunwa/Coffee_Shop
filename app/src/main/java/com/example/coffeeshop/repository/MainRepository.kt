package com.example.coffeeshop.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffeeshop.model.CoffeeModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadFiltered(id: Int): LiveData<MutableList<CoffeeModel>> {
        val listData = MutableLiveData<MutableList<CoffeeModel>>()
        val ref = firebaseDatabase.getReference("items")
        val query = ref.orderByChild("categoryId").equalTo(id.toString())
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Repository", "onDataChange: $snapshot")
                val lists = mutableListOf<CoffeeModel>()
                for (childSnapShot in snapshot.children) {
                    val item = childSnapShot.getValue(CoffeeModel::class.java)
                    item?.let { lists.add(it) }
                }
                Log.d("Repository", "onDataChange: ${lists.size}")
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listData
    }
}