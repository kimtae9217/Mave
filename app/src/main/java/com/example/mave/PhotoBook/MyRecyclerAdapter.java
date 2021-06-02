package com.example.mave.PhotoBook;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mave.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    Context context;
    List<ItemData> itemdata;

    public MyRecyclerAdapter(Context context, List<ItemData> familyPhoto) {
        this.context = context;
        this.itemdata = familyPhoto;
    }

    @NonNull
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, int position) {
        ItemData itemData = itemdata.get(position);
        holder.tvTitle.setText(itemData.getTitle());
        holder.tvContent.setText(itemData.getContent());

        String imageUri = null;
        imageUri = itemData.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle, tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.list_item_familypicture);
            tvTitle = itemView.findViewById(R.id.list_item_title);
            tvContent = itemView.findViewById(R.id.list_item_content);

        }
    }
}