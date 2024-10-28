package com.example.drivingtestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyArrayAdapter extends RecyclerView.Adapter<MyArrayAdapter.ViewHolder> {

    private List<Exam> mExams;

    public MyArrayAdapter(List<Exam> exams) {
        mExams = exams;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView deImageView;
        public TextView deTextView;
        public TextView soluongCauhoiTextView;
        public Button lamBaiButton;

        public ViewHolder(View itemView) {
            super(itemView);

            deImageView = itemView.findViewById(R.id.imgDe);
            deTextView = itemView.findViewById(R.id.txtDe);
            soluongCauhoiTextView = itemView.findViewById(R.id.txtSoluongcauhoi);
            lamBaiButton = itemView.findViewById(R.id.btnLamBai);
        }
    }

    @NonNull
    @Override
    public MyArrayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View examView = inflater.inflate(R.layout.exam_list, parent, false);

        // Return a new holder instance
        return new ViewHolder(examView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArrayAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Exam exam = mExams.get(position);

        // Set item views based on your views and data model
        TextView textViewDe = holder.deTextView;
        textViewDe.setText(exam.getNamedethi());

        TextView textViewSoluongCauhoi = holder.soluongCauhoiTextView;
        String cauhoiVaThoigian = exam.getSoluongCauhoi() + " câu/" + exam.getThoigian() + " phút";
        textViewSoluongCauhoi.setText(cauhoiVaThoigian);

        ImageView imageView = holder.deImageView;
//        imageView.setImageResource(exam.getImagedethi());
        imageView.setImageResource(R.drawable.pencil);

        // Set up "Làm bài" button
        Button buttonLamBai = holder.lamBaiButton;
        buttonLamBai.setText("Làm bài");
        buttonLamBai.setEnabled(true);  // Optional: adjust the state if needed

    }

    @Override
    public int getItemCount() {
        return mExams.size();
    }
}
