package com.example.shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
        Content content = contents.get(position);
        holder.titleTxt.setText(content.getName());
        holder.feeTxt.setText(String.valueOf(content.getPrice())+" đ");
        holder.Loading.setVisibility(View.VISIBLE);
//        holder.pic.setEnable=false;
//        callapi
//        {
//            holder.Loading.setVisibility(View.GONE);
//            holder.pic.setEnable=true;
//        }
//f
//        int drawableResourceId = holder.itemView.getResources().getIdentifier(contents.get(position).getPicUrl(),
//                "drawable",holder.itemView.getContext().getPackageName());
//
//
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .transform(new GranularRoundedCorners(30,30,0,0))
//                .into(holder.pic);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("object",contents.get(position));
            holder.itemView.getContext().startActivity(intent);

        });
    }



    @Override
    public int getItemCount() {
        return contents.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt,feeTxt;
        ProgressBar Loading;
//        ImageView pic;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            titleTxt = (TextView)itemView.findViewById(R.id.titleTxt);
            feeTxt = (TextView)itemView.findViewById(R.id.feeTxt);
            Loading = itemView.findViewById(R.id.Loading);

//            pic = itemView.findViewById(R.id.pic);
        }
    }
}
