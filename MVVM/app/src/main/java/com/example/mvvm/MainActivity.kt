package com.example.mvvm

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mvvm.adapter.BaseAdapter
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.listener.PaginationScrollListener
import com.example.mvvm.model.User
import com.example.mvvm.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(),
    SwipeRefreshLayout.OnRefreshListener {

    private val mContext: Context = this@MainActivity

    private lateinit var binding: ActivityMainBinding

    lateinit var userViewModel: UserViewModel
    lateinit var adapter: BaseAdapter

    private val PAGE_START = 1
    private var currentOffSet = PAGE_START

    private var isLastPage = false

    private var userArrayList: ArrayList<User> = ArrayList()

    val itemCount = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(mContext as Activity, R.layout.activity_main)

        binding.rvUsers.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)

        binding.swipeRefreshLayout.setOnRefreshListener(this)

        binding.rvUsers.addOnScrollListener(object :
            PaginationScrollListener(binding.rvUsers.layoutManager as LinearLayoutManager) {

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun loadMoreItems() {
                currentOffSet += 1
                loadUserData()
            }
        })

        loadUserData()

        userViewModel.userList.observe(this, Observer {
            binding.swipeRefreshLayout.isRefreshing = false
            userArrayList.addAll(it)
            adapter = BaseAdapter(mContext, userArrayList, R.layout.item_user)
            binding.rvUsers.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    fun loadUserData() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.loadUsers(currentOffSet, itemCount)
    }

    override fun onRefresh() {
        binding.swipeRefreshLayout.isRefreshing = true
        currentOffSet = PAGE_START
        loadUserData()
    }
}