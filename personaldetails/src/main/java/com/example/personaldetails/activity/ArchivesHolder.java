package com.example.personaldetails.activity;


import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personaldetails.R;


public class ArchivesHolder extends RecyclerView.ViewHolder{

    public ImageView arImg;

    public ArchivesHolder(@NonNull View itemView) {
        super(itemView);
        arImg = itemView.findViewById(R.id.ar_img);
    }
}
