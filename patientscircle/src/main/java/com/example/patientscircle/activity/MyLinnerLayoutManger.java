package com.example.patientscircle.activity;


import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
 *@Auther:陈浩
 *@Date: 2019/8/19
 *@Time:18:56
 *@Description:${DESCRIPTION}
 * */public class MyLinnerLayoutManger extends LinearLayoutManager {
    public MyLinnerLayoutManger(Context context) {
        super(context);
    }

    public MyLinnerLayoutManger(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLinnerLayoutManger(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
