package com.example.end_project.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.end_project.AClickListener;
import com.example.end_project.Adapters.AdviceAdapter;
import com.example.end_project.Advices;
import com.example.end_project.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheet extends BottomSheetDialogFragment {
    private ArrayList<Advices> advices;
    AClickListener aClickListener;

    public BottomSheet(ArrayList<Advices> advices, AClickListener aClickListener) {
        this.advices = advices;
        this.aClickListener = aClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet, null);
        bottomSheetDialog.setContentView(view);
        RecyclerView rv_adv = view.findViewById(R.id.rv_adv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_adv.setLayoutManager(linearLayoutManager);
        AdviceAdapter adviceAdapter = new AdviceAdapter(advices, new AClickListener() {
            @Override
            public void clickItem(Advices advice) {
                aClickListener.clickItem(advice);
            }
        });
        rv_adv.setAdapter(adviceAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rv_adv.addItemDecoration(itemDecoration);
        return  bottomSheetDialog;
    }
}
