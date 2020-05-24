package com.example.covid19.districtwise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.covid19.R;

import java.util.ArrayList;

public class DatalistAdapter extends ArrayAdapter<DataDistrict> {
    private Context rcontext;
    private int rresource;
    static class ViewHolder{
        TextView district;
        TextView confirm;
        TextView active;
        TextView recover;
    }
    public DatalistAdapter(Context context, int resource, ArrayList<DataDistrict> objects) {
        super(context, resource, objects);
        rcontext =context;
        rresource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String district=getItem(position).getDistrict();
        String confirmed =getItem(position).getConfirmed();
        String active =getItem(position).getActive();
        String recover=getItem(position).getRecover();
        final View result;
        ViewHolder holder;

        DataDistrict datadis=new DataDistrict(district,confirmed,recover,active);
        if(convertView ==null) {
            LayoutInflater inflater = LayoutInflater.from(rcontext);
            convertView = inflater.inflate(rresource, parent, false);
            holder = new ViewHolder();
            holder.district = (TextView) convertView.findViewById(R.id.districtTv);
            holder.confirm = (TextView) convertView.findViewById(R.id.confirmTv);
            holder.active = (TextView) convertView.findViewById(R.id.activeTv);
            holder.recover=convertView.findViewById(R.id.recoverTv);
            result = convertView;
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
            result=convertView;
        }
        holder.district.setText(district);
        holder.confirm.setText(confirmed);
        holder.active.setText(active);
        holder.recover.setText(recover);

        return convertView;
    }
}
