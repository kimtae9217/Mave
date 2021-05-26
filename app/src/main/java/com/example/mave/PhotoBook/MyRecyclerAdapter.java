package com.example.mave.PhotoBook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mave.R;

import java.util.ArrayList;

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
        holder.familypicture.setImageResource(arrayList.get(position).getFamilyphoto());
        holder.content.setText(arrayList.get(position).getContent());
        holder.title.setText(arrayList.get(position).getTitle());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FragmentPage1.class);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return (null != arrayList? arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView familypicture;
        protected TextView content;
        protected TextView title;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.familypicture = (ImageView)itemView.findViewById(R.id.list_item_familypicture);
            this.content = (TextView)itemView.findViewById(R.id.list_item_content);
            this.title = (TextView)itemView.findViewById(R.id.list_item_title);
        }
    }
}