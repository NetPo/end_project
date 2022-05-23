package com.example.end_project.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.end_project.Activities.FloorActivity;
import com.example.end_project.M_RV;
import com.example.end_project.R;

import java.util.ArrayList;
import java.util.List;

public class M_Adapter extends RecyclerView.Adapter<M_Adapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<M_RV> malls;
    private Context ctx;



    public M_Adapter(Context context, List<M_RV> malls) {
        this.malls = malls;
        this.inflater = LayoutInflater.from(context);
    }


    public void setItems(ArrayList<M_RV> mall){
        malls.addAll(mall);
    }



    @NonNull
    @Override
    public M_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        ctx = parent.getContext();
        return new ViewHolder(view);
    }

    //@SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull M_Adapter.ViewHolder holder, int position) {
        String name;
        String floors_am;
        M_RV m_rv = malls.get(position);
        holder.info.setText(m_rv.getName());
        name = m_rv.getName();
        floors_am = m_rv.getFloors_am();
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent228 = new Intent(ctx, FloorActivity.class);
                intent228.putExtra("Название выбранного тц", name);
                intent228.putExtra("Количество этажей", floors_am);
                ctx.startActivity(intent228);
            }
        });
        //holder.nameView.setText("Название: " + m_rv.getName());
        //holder.floorView.setText("Этажей: " + m_rv.getFloors_am());
    }

    @Override
    public int getItemCount() {
        return malls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //TextView nameView;
        //TextView floorView;
        Button info;

        ViewHolder(View view) {
            super(view);
            info = view.findViewById(R.id.button_RV);
            //nameView = view.findViewById(R.id.name);
            //floorView = view.findViewById(R.id.floors);
        }
    }
}
