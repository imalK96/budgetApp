package com.example.Heshan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.imal.R;

import java.util.ArrayList;

public class customListView extends ArrayAdapter<String> {

    private  Integer imageId;
    private ArrayList list;
    private Activity context;

    public customListView(Activity context,ArrayList list, Integer imageId){
        super(context, R.layout.custom_cell,list);

        this.context = context;
        this.list = list;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;

        if(r == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.custom_cell,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)r.getTag();

        }
        viewHolder.t1.setText(list.get(position).toString());
        viewHolder.i1.setImageResource(imageId);

        return r;
    }

    class ViewHolder{
        TextView t1;
        ImageView i1;

        ViewHolder(View v){
            t1 = v.findViewById(R.id.nameText);
            i1 = v.findViewById(R.id.imageView);
        }
    }
}

