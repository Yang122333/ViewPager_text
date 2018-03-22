package com.example.administrator.viewpager_text;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static android.provider.Telephony.Mms.Part.FILENAME;

/**
 * Created by ${Yang} on 2018/3/19.
 */

public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewLists;
    private Button btn;
    private StringBuilder sb = new StringBuilder();
    private ArrayList<Item> itemArrayList;
    public MyPagerAdapter() {
    }
    public MyPagerAdapter(ArrayList<View> viewLists) {
        super();
        this.viewLists = viewLists;
    }

    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(viewLists.get(position));
        if (position == 0) {
            itemArrayList=read(container.getContext());
            Button btn=(Button)viewLists.get(position).findViewById(R.id.btn1);
            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write(v.getContext(),(EditText) viewLists.get(position).findViewById(R.id.edittext1));
            }
        });


            ListView listView = (ListView) viewLists.get(position).findViewById(R.id.listview1);
            MyListAdapter myListAdapter = new MyListAdapter(itemArrayList,container.getContext());
            listView.setAdapter(myListAdapter);
        }

        return viewLists.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }
    public void write(Context c,EditText editText) {
        String string = editText.getText().toString();
        if (!string.isEmpty()) {
//写入
            try {
                Item item = new Item();
                item.setMessage(editText.getText().toString());
                item.setPhoto(R.drawable.photo1);
                itemArrayList.add(item);
                FileOutputStream fos = c.openFileOutput(FILENAME, Context.MODE_APPEND);
                fos.write(string.getBytes());//写文件。
                fos.write("\t".getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            editText.setText(null);


        }

    }
    public ArrayList<Item> read(Context c){
// 读取文件
        ArrayList<Item> itemArrayList1=new ArrayList<>();
        try {

            FileInputStream inStream = c.openFileInput(FILENAME);
            int len = 0;
            byte[] buf = new byte[1024];

            while ((len = inStream.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            inStream.close();
            ArrayList<String> list = new ArrayList(Arrays.asList(sb.toString().split("\t")));

            if (list != null) {
                for (String str : list) {
                    Item item = new Item();
                    item.setMessage(str);
                    item.setPhoto(R.drawable.photo0);
                    itemArrayList1.add(item);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return itemArrayList1;
    }
}