package com.example.end_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class M_Adapter extends RecyclerView.Adapter<M_Adapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<M_RV> malls;

    M_Adapter(Context context, List<M_RV> malls) {
        this.malls = malls;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public M_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull M_Adapter.ViewHolder holder, int position) {
        M_RV m_rv = malls.get(position);
        holder.info.setText(m_rv.getName());
        //holder.nameView.setText("Название: " + m_rv.getName());
        //holder.floorView.setText("Этажей: " + m_rv.getFloors_am());
    }

    @Override
    public int getItemCount() {
        return malls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameView;
        TextView floorView;
        Button info;

        ViewHolder(View view) {
            super(view);
            info = view.findViewById(R.id.button_RV);
            //nameView = view.findViewById(R.id.name);
            //floorView = view.findViewById(R.id.floors);
        }
    }
}
