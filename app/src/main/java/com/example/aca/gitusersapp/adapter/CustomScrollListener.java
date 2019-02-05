package com.example.aca.gitusersapp.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



public abstract class CustomScrollListener extends RecyclerView.OnScrollListener {

    private boolean isLoading = false;
    private boolean isLastPage = false;

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    private LinearLayoutManager mLayoutManager;

    public CustomScrollListener(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = mLayoutManager.getChildCount();
        int totalItemCount = mLayoutManager.getItemCount();
        int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

        if (!isLoading && !isLastPage) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                onLoadMore(totalItemCount);
                isLoading = true;
            }
        }
    }

    public abstract void onLoadMore(int totalItemsCount);

}
