package com.example.shop.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.shop.Domain.Content;
import com.example.shop.Domain.PopularDomain;

import java.util.ArrayList;
import java.util.Date;

public class ManagmentCart {
    private Context context;
    private TinyDB tinyDB;
    private Content content;

    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB =new TinyDB(context);
    }
    public void insertFood(PopularDomain item){
        ArrayList<PopularDomain> listPop = getListCart();
        boolean exitAlready=false;
        int n=0;
        for (int i = 0; i < listPop.size(); i++){
            if(listPop.get(i).getTitle().equals(item.getTitle())){
                exitAlready = true;
                n = i;
                break;
            }
        }
        if(exitAlready){
            listPop.get(n).setNumberinCart(item.getNumberinCart());
        }else{
            listPop.add(item);
        }
        tinyDB.putListObject("CartList",listPop);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<PopularDomain> getListCart() {
            return tinyDB.getListObject("CartList");
    }
    public void minusNumberItem(ArrayList<PopularDomain>listItem,int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(listItem.get(position).getNumberinCart() == 1){
            listItem.remove(position);
        } else{
            listItem.get(position).setNumberinCart(listItem.get(position).getNumberinCart() - 1);
        }
        tinyDB.putListObject("CarList", listItem);
        changeNumberItemsListener.change();
    }
    public void plusNumberItem(ArrayList<PopularDomain>listItem,int position, ChangeNumberItemsListener changeNumberItemsListener){
        listItem.get(position).setNumberinCart(listItem.get(position).getNumberinCart() + 1);
        tinyDB.putListObject("CarList", listItem);
        changeNumberItemsListener.change();
    }
    public Double getTotalFee(){
        ArrayList<PopularDomain> listItem = getListCart();
        double fee = 0;
        for(int i=0; i < listItem.size(); i++){
            fee = fee + (listItem.get(i).getPrice() * listItem.get(i).getNumberinCart());
        }
        return fee;
    }
}
