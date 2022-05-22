package com.example.end_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.AdvViewHolder> {

    private ArrayList<Advices> advices;
    private AClickListener aClickListener;

    public AdviceAdapter(ArrayList<Advices> advices, AClickListener aClickListener){
        this.advices = advices;
        this.aClickListener = aClickListener;
    }

    @NonNull
    @Override
    public AdvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advice_item, parent, false);
        return new AdvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvViewHolder holder, int position) {
        Advices ad = advices.get(position);
        holder.txt.setText(ad.getAdv());
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aClickListener.clickItem(ad);
            }
        });

    }

    @Override
    public int getItemCount() {
        return advices.size();
    }

    public class AdvViewHolder extends RecyclerView.ViewHolder{
       private TextView txt;

        public AdvViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.tv_adv);
        }
    }
}
