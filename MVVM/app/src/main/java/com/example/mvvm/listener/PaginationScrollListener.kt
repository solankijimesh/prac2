package com.example.mvvm.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(var layoutManager: LinearLayoutManager?) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItems = layoutManager?.childCount
        val totalItems = layoutManager?.itemCount!!
        val firstItemPosition = layoutManager?.findFirstVisibleItemPosition()

        if (!isLastPage()) {
            if ((visibleItems?.plus(firstItemPosition!!))!! >= totalItems && firstItemPosition!! >= 0 && totalItems >= 10) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

}