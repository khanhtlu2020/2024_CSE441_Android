package com.example.drivingtestapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ExamListActivity extends AppCompatActivity {

    ArrayList<Exam> exams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);

        // Lookup the recyclerview in activity layout
        RecyclerView recyclerView_dethi = (RecyclerView) findViewById(R.id.recyclerView_dethi);

        // Initialize contacts
        exams = Exam.createExamsList(20);
        // Create adapter passing in the sample user data
        MyArrayAdapter adapter = new MyArrayAdapter(exams);
        // Attach the adapter to the recyclerview to populate items
        recyclerView_dethi.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView_dethi.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }
}