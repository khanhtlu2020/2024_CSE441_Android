package com.example.drivingtestapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.List;

public class MyArrayAdapter extends RecyclerView.Adapter<MyArrayAdapter.ViewHolder> {
    private List<Exam> mExams;

    // Array of activities for different exams
    private static final Class[] activities = new Class[]{
            ExamActivity.class,  // Activity cho Đề số 1
            ExamActivity1.class, // Activity cho Đề số 2
            ExamActivity2.class,
            ExamActivity3.class,
            ExamActivity4.class,
            // Thêm các Activity khác tương ứng với các đề thi tiếp theo
    };

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
            deTextView = itemView.findViewById(R.id.txtTenLuat);
            soluongCauhoiTextView = itemView.findViewById(R.id.txtMucPhat);
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
        imageView.setImageResource(R.drawable.exam);

        // Set up "Làm bài" button
        Button buttonLamBai = holder.lamBaiButton;
        buttonLamBai.setText("Làm bài");
        buttonLamBai.setEnabled(true);  // Optional: adjust the state if needed

        // Handle button click
        buttonLamBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                // Hiển thị dialog xác nhận
                new AlertDialog.Builder(context)
                        .setTitle("BẮT ĐẦU LÀM BÀI")
                        .setMessage("\nĐề thi gồm 25 câu hỏi làm bài trong thời gian 19 phút.\n" +
                                "\nĐể vượt qua bài thi, bạn cần trả lời đúng 21/25 câu hỏi.")
                        .setPositiveButton("Bắt đầu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Khởi động activity khi người dùng chọn "Bắt đầu"
                                Class activityToStart = activities[position % activities.length];
                                Intent intent = new Intent(context, activityToStart);
                                intent.putExtra("EXAM_ID", exam.getNamedethi());
                                context.startActivity(intent);
                            }
                        })
                        .setNegativeButton("Hủy", null) // Không làm gì khi nhấn "Hủy"
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExams.size();
    }
}
