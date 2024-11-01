package com.example.drivingtestapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LawAdapter extends RecyclerView.Adapter<LawAdapter.ViewHolder> {

    private Activity context;
    private ArrayList<Law> myArray;
    private int layoutId;

    public LawAdapter(Activity context, int layoutId, ArrayList<Law> arr) {
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View itemView = inflater.inflate(layoutId, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Law myLaw = myArray.get(position);

        holder.tenLuat.setText(myLaw.getTenLuat());
        holder.mucPhat.setText(myLaw.getMucPhat());

    }

    @Override
    public int getItemCount() {
        return myArray.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenLuat, mucPhat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenLuat = itemView.findViewById(R.id.txtTenLuat);
            mucPhat = itemView.findViewById(R.id.txtMucPhat);
        }
    }
}
