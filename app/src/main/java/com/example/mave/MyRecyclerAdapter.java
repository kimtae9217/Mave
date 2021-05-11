package com.example.mave;

import android.app.LauncherActivity;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<ItemData> arrayList;

    public MyRecyclerAdapter(ArrayList<ItemData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyRecyclerAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.CustomViewHolder holder, int position) {
        /*holder.familypicture.setImageResource(arrayList.get(position).getFamilyphoto());*/
        holder.content.setText(arrayList.get(position).getContent());
        holder.title.setText(arrayList.get(position).getTitle());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LauncherActivity.ListItem.class);
                /*context.startActivity(intent);*/
            }
        });
    }
    @Override
    public int getItemCount() {
        return (null != arrayList? arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        /*protected ImageView familypicture;*/
        protected TextView content;
        protected TextView title;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            /*this.familypicture = (ImageView)itemView.findViewById(R.id.familypicture);*/
            this.content = (TextView)itemView.findViewById(R.id.txt_content);
            this.title = (TextView)itemView.findViewById(R.id.txt_title);
        }
    }
}