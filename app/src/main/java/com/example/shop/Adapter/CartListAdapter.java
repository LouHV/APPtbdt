package com.example.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.shop.Domain.DetailCart;
import com.example.shop.Domain.PopularDomain;
import com.example.shop.Helper.ChangeNumberItemsListener;
import com.example.shop.Helper.ManagmentCart;
import com.example.shop.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<DetailCart> listItemSelected;
    Context context;


    public CartListAdapter(ArrayList<DetailCart> listItemSelected) {
        this.listItemSelected = listItemSelected;

    }


    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        holder.title.setText(String.valueOf(listItemSelected.get(position).getQuantity()) );
        holder.feeEachItem.setText("$"+listItemSelected.get(position).getPrice());
        holder.totalEachItem.setText("$"+Math.round(listItemSelected.get(position).getQuantity() * listItemSelected.get(position).getPrice()));
        holder.num.setText(String.valueOf(listItemSelected.get(position).getQuantity()));


        Glide.with(holder.itemView.getContext())
                .load("http://192.168.1.36:8080/api/product/getImage/"+listItemSelected.get(position).getProductId())
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(holder.pic);

//        holder.plusItem.setOnClickListener(v -> {
//            managmentCart.plusNumberItem(listItemSelected, position, () -> {
//                notifyDataSetChanged();
//                changeNumberItemsListener.change();
//            });
//
//        });
//        holder.minusItem.setOnClickListener(v -> {
//            managmentCart.minusNumberItem(listItemSelected, position, () -> {
//                notifyDataSetChanged();
//                changeNumberItemsListener.change();
//            });
//
//        });
    }

    @Override
    public int getItemCount() {
        return listItemSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem, plusItem, minusItem;
        ImageView pic;
        TextView totalEachItem, num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.pic);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.puldCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            num = itemView.findViewById(R.id.numberItem);
        }
    }
}