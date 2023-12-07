package com.example.shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.shop.Activity.DetailActivity;
import com.example.shop.Domain.Content;
import com.example.shop.Domain.PopularDomain;
import com.example.shop.Domain.Product;
import com.example.shop.R;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Viewholder>  {
    ArrayList<Content> contents;
    private static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_LOADING = 1;
    Context context;
    public ProductAdapter(ArrayList<Content> contents) {

        this.contents = contents;
    }

    @NonNull
    @Override
    public ProductAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profuct_list,parent,false);
            context = parent.getContext();
            return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.Viewholder holder, int position) {

            Viewholder viewholder = (Viewholder) holder;
            Content content = contents.get(position);
            viewholder.titleTxt.setText(content.getName());
            viewholder.feeTxt.setText(String.valueOf(content.getPrice())+" đ");


            Glide.with(viewholder.itemView.getContext())
                    .load("http://10.0.0.87:8080/api/product/getImage/"+content.getId())
                    .transform(new GranularRoundedCorners(30,30,0,0))
                    .into(viewholder.pic);


            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                //đẩy sang detail
                intent.putExtra("chitiet",contents.get(position));
                holder.itemView.getContext().startActivity(intent);
            });

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt,feeTxt;

        ImageView pic;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            titleTxt = (TextView)itemView.findViewById(R.id.titleTxt);
            feeTxt = (TextView)itemView.findViewById(R.id.feeTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
