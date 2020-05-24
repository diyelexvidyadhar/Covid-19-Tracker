package com.example.covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StateDataAdapter extends ArrayAdapter<StateData> {
    private Context mcontext;
    private int lastposition=-1;
    private int mresource;
    static class ViewHolder{
        TextView state;
        TextView confirm;
        TextView recover;
        TextView death;
    }
    public StateDataAdapter( Context context, int resource, ArrayList<StateData> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.mresource =resource;
    }
    //
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String state =getItem(position).getState();
        String confirm=getItem(position).getConfirmState();
        String recover=getItem(position).getRecoverState();
        String death =getItem(position).getDeathState();
        final View result;
        ViewHolder holder;

        StateData data=new StateData(state,confirm,recover,death);
        if(convertView ==null) {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            convertView = inflater.inflate(mresource, parent, false);
            holder = new ViewHolder();
            holder.state = (TextView) convertView.findViewById(R.id.State_main);
            holder.confirm = (TextView) convertView.findViewById(R.id.confirmed_state);
            holder.recover=convertView.findViewById(R.id.recover_state);
            holder.death = (TextView) convertView.findViewById(R.id.death_state);
            result = convertView;
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
            result=convertView;
        }
        Animation animation= AnimationUtils.loadAnimation(mcontext,
                (position>lastposition) ? R.anim.loading_down_anim :R.anim.loading_up_anim);
        result.startAnimation(animation);
        lastposition =position;
        holder.state.setText(state);
        holder.confirm.setText(confirm);
        holder.recover.setText(recover);
        holder.death.setText(death);

        return convertView;
    }
}
