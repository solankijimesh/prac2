package com.example.mvvm

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.adapter.BaseAdapter
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private val mContext: Context = this@MainActivity

    private lateinit var binding: ActivityMainBinding

    lateinit var userViewModel: UserViewModel
    lateinit var adapter: BaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(mContext as Activity, R.layout.activity_main)

        binding.rvUsers.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)

        userViewModel = UserViewModel()
        userViewModel.loadUsers()

        userViewModel.userList.observe(this, Observer {
            adapter = BaseAdapter(mContext, it, R.layout.item_user)
            binding.rvUsers.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }
}