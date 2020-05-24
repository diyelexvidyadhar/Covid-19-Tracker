package com.example.covid19.Symptomhelper.preventionhelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19.R;
import com.example.covid19.Symptomhelper.SymptomHelper;

import java.util.ArrayList;

public class PreventionAdapter extends RecyclerView.Adapter<PreventionAdapter.preventionViewHolder> {
    ArrayList<PreventionHelper> preventionHelpers;
    public PreventionAdapter(ArrayList<PreventionHelper> preventionHelpers) {
        this.preventionHelpers = preventionHelpers;
    }

    @NonNull
    @Override
    public preventionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.prevention_carddesign,parent,false);
        preventionViewHolder preventionViewHolder=new preventionViewHolder(view);
        return preventionViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull preventionViewHolder holder, int position) {
        PreventionHelper preventionHelper=preventionHelpers.get(position);
        holder.image.setImageResource(preventionHelper.getImage());
        holder.title.setText(preventionHelper.getTitle());
        holder.descrption.setText(preventionHelper.getDescription());

    }

    @Override
    public int getItemCount() {
        return preventionHelpers.size();
    }

    public static class preventionViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title,descrption;

        public preventionViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.prevention_image);
            title=itemView.findViewById(R.id.prevention_title);
            descrption=itemView.findViewById(R.id.prevention_desciption);

        }
    }
}
