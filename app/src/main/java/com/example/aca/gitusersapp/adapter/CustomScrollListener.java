package com.example.aca.gitusersapp.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



public abstract class CustomScrollListener extends RecyclerView.OnScrollListener {

    private static int ITEMS_COUNT = 10;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 0;

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

//    @Override
//    public void onScrolled(@NonNull RecyclerView view, int dx, int dy) {
//        super.onScrolled(view, dx, dy);
//        int lastVisibleItemPosition = 0;
//        int totalItemCount = mLayoutManager.getItemCount();
//
//
//        lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
//
//        if (loading && (totalItemCount > previousTotalItemCount)) {
//            loading = false;
//            previousTotalItemCount = totalItemCount;
//        }
//
//        if (!loading && (lastVisibleItemPosition + VISIBLE_TRESHHOLD) > totalItemCount) {
//            currentPage++;
//            onLoadMore(currentPage, totalItemCount, view);
//            loading = true;
//        }
//    }

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
                currentPage++;
                onLoadMore(totalItemCount);
                isLoading = true;
            }
        }
    }

    public abstract void onLoadMore(int totalItemsCount);

}
