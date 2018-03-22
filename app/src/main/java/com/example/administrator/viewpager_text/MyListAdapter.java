package com.example.administrator.viewpager_text;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ${Yang} on 2018/3/20.
 */
class Item{
    private String message;
    private  int photo;
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public int getPhoto(){
        return photo;
    }
    public void setPhoto(int photo){
        this.photo=photo;
    }
}
public class MyListAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<Item> itemArrayList;

    public MyListAdapter(){}

    public  MyListAdapter(ArrayList<Item> itemArrayList, Context context){
        this.inflater=LayoutInflater.from(context);
        this.itemArrayList=itemArrayList;
    }
    @Override
    public int getCount() {
        return itemArrayList==null ?0:itemArrayList.size();
    }

    @Override
    public Item getItem(int position) {
        return itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.item,null);
        Item item=getItem(position);
        TextView tv_message= (TextView) view.findViewById(R.id.text);
//        ImageView image_photo= (ImageView) view.findViewById(R.id.image_photo);
        tv_message.setText(item.getMessage());
//        image_photo.setImageResource(item.getPhoto());
        return view;
    }
}
