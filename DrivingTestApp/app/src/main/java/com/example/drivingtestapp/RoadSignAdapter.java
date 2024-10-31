package com.example.drivingtestapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RoadSignAdapter extends RecyclerView.Adapter<RoadSignAdapter.ViewHolder> {
    private Activity context;
    private ArrayList<RoadSign> roadSignList;
    private int layoutId;

    public RoadSignAdapter(Activity context, int layoutId, ArrayList<RoadSign> roadSignList) {
        this.context = context;
        this.layoutId = layoutId;
        this.roadSignList = roadSignList;
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
        RoadSign roadSign = roadSignList.get(position);
        holder.tenBienBao.setText(roadSign.getTenBienBao());
        holder.noiDungBienBao.setText(roadSign.getNoidungBienBao());
        // Load image resource by name
        int resId = context.getResources().getIdentifier(roadSign.getHinh(), "drawable", context.getPackageName());
        holder.hinh.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return roadSignList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenBienBao, noiDungBienBao;
        ImageView hinh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenBienBao = itemView.findViewById(R.id.txtSignName);
            noiDungBienBao = itemView.findViewById(R.id.txtSignMeaning);
            hinh = itemView.findViewById(R.id.imgSign);
        }
    }
}
