package com.example.drivingtestapp;

import java.util.ArrayList;

public class Exam {
    private String namedethi;
    private int imagedethi;  // Sử dụng kiểu int cho ID tài nguyên ảnh
    private int soluongCauhoi;
    private int thoigian; // Thời gian làm bài, tính bằng phút

    // Constructor với các thuộc tính mới
    public Exam(String namedethi, int imagedethi, int soluongCauhoi, int thoigian) {
        this.namedethi = namedethi;
        this.imagedethi = imagedethi;
        this.soluongCauhoi = soluongCauhoi;
        this.thoigian = thoigian;
    }

    // Getter và Setter cho các thuộc tính
    public String getNamedethi() {
        return namedethi;
    }

    public void setNamedethi(String namedethi) {
        this.namedethi = namedethi;
    }

    public int getImagedethi() {
        return imagedethi;
    }

    public void setImagedethi(int imagedethi) {
        this.imagedethi = imagedethi;
    }

    public int getSoluongCauhoi() {
        return soluongCauhoi;
    }

    public void setSoluongCauhoi(int soluongCauhoi) {
        this.soluongCauhoi = soluongCauhoi;
    }

    public int getThoigian() {
        return thoigian;
    }

    public void setThoigian(int thoigian) {
        this.thoigian = thoigian;
    }

    private static int lastExamId = 0;

    // Tạo danh sách đề thi
    public static ArrayList<Exam> createExamsList(int numExams) {
        ArrayList<Exam> exams = new ArrayList<>();
        lastExamId = 0;

        for (int i = 1; i <= numExams; i++) {
            // Ví dụ, có thể tạo các đề thi với số lượng câu hỏi và thời gian khác nhau
            int soluongCauhoi = 25;
            int thoigian = 19;

            // Sử dụng ID tài nguyên ảnh của Android để minh họa
            int imagedethi = android.R.drawable.checkbox_off_background;

            exams.add(new Exam("Đề số " + ++lastExamId, imagedethi, soluongCauhoi, thoigian));
        }

        return exams;
    }
}
