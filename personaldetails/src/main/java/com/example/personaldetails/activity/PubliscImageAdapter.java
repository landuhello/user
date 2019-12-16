package com.example.personaldetails.activity;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.personaldetails.R;


import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/8/21
 *@Time:19:30
 *@Description:${DESCRIPTION}
 * */public class PubliscImageAdapter extends RecyclerView.Adapter<PubliscImageAdapter.ViewhOler> {
    private Context context;
    private ArrayList strings;

    public PubliscImageAdapter(Context context, ArrayList strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public ViewhOler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new ViewhOler(from.inflate(R.layout.images, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewhOler viewhOler, final int i) {
        Glide.with(context).load(strings.get(i)).into(viewhOler.img);


        if(strings.get(i).toString().equals("2131623936")){
            viewhOler.x.setVisibility(View.GONE);
        }else{
            viewhOler.x.setVisibility(View.VISIBLE);
        }

        viewhOler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click != null) {
                    click.click();
                }
            }
        });

        //点击差X
        viewhOler.x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delect.click(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return strings.size();
    }


    class ViewhOler extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final ImageView x;

        public ViewhOler(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.publish_img);
            x = itemView.findViewById(R.id.publish_x);
        }
    }

    public void setClick(PuImgClick click) {
        this.click = click;
    }

    private PuImgClick click;

    public interface PuImgClick {
        void click();
    }

    public void setDelect(PuImgDelect delect) {
        this.delect = delect;
    }

    private PuImgDelect delect;
    public interface PuImgDelect {
        void click(int i);
    }
}
