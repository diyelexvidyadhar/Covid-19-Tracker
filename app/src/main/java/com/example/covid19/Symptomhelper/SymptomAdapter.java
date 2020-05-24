package com.example.covid19.Symptomhelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19.R;

import java.util.ArrayList;

public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.symptomViewHolder> {
    ArrayList<SymptomHelper> symptomHelpers;

    public SymptomAdapter(ArrayList<SymptomHelper> symptomHelpers) {
        this.symptomHelpers = symptomHelpers;
    }

    @NonNull
    @Override
    public symptomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.symptoms_card_design,parent,false);
        symptomViewHolder symptomViewHolder=new symptomViewHolder(view);
        return symptomViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull symptomViewHolder holder, int position) {
SymptomHelper symptomHelper=symptomHelpers.get(position);
        holder.image.setImageResource(symptomHelper.getImage());
holder.title.setText(symptomHelper.getTitle());
    }

    @Override
    public int getItemCount() {
        return symptomHelpers.size();
    }

    public static class symptomViewHolder extends RecyclerView.ViewHolder{
ImageView image;
TextView title;
        public symptomViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.symptom_image);
            title=itemView.findViewById(R.id.symptom_title);

        }
    }
}
