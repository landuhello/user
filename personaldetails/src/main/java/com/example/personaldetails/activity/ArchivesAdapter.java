package com.example.personaldetails.activity;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.personaldetails.R;



import java.util.ArrayList;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public class ArchivesAdapter extends RecyclerView.Adapter<ArchivesHolder> {
    private Context context;
    private ArrayList<String> list;

    public ArchivesAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ArchivesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new ArchivesHolder(from.inflate(R.layout.archives_picture,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArchivesHolder archivesHolder, int i) {

        Glide.with(context).load(list.get(i)).into(archivesHolder.arImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

