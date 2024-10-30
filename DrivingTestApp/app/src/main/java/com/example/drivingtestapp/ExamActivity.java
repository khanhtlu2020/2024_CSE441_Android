package com.example.drivingtestapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.drivingtestapp.models.Question;
import com.example.drivingtestapp.services.ApiService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamActivity extends AppCompatActivity {
    public static int score = 20;
    private ArrayList<Question> questions = new ArrayList<>();
    ViewPager2 viewPager;
    Button btnPrev, btnNext, btnSubmit;
    TextView cur, total, cDown;
    CountDownTimer countDownTimerTotal;
    int timeLeftInMillisTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        viewPager = findViewById(R.id.viewpager);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        btnSubmit = findViewById(R.id.btnSubmit); // Tìm button nộp bài
        cur = findViewById(R.id.current);
        total = findViewById(R.id.total);
        cDown = findViewById(R.id.countDown);

        timeLeftInMillisTotal = 1140000; // 19 phút cho toàn bộ bài thi
        countDownTimerTotal = new CountDownTimer(timeLeftInMillisTotal, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillisTotal = (int) millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent();
                intent.putExtra("result", score);
                setResult(RESULT_OK, intent);
                ExamActivity.this.finish();
            }
        }.start();

        getQuestions();

        // Handle previous button click
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousItem = viewPager.getCurrentItem() - 1;
                if (previousItem >= 0) {
                    viewPager.setCurrentItem(previousItem);
                }
            }
        });

        // Handle next button click
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextItem = viewPager.getCurrentItem() + 1;
                if (nextItem < questions.size()) {
                    viewPager.setCurrentItem(nextItem);
                }
            }
        });

        // Handle submit button click
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitExam(); // Gọi hàm submitExam khi nhấn button
            }
        });
    }

    private void getQuestions() {
        ApiService.apiService.questionCall().enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                questions = new ArrayList<>(response.body());
                for (Question question : questions) {
                    Log.i("QuestionsLog", question.toString());
                }
                viewPager.setUserInputEnabled(false);
                ViewPagerAdapter adapter = new ViewPagerAdapter(ExamActivity.this, questions);
                viewPager.setAdapter(adapter);
                cur.setText("1");
                total.setText(String.valueOf(questions.size()));
                viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        cur.setText(String.valueOf(position + 1));
                        updateButtonVisibility(position);
                    }
                });
                updateButtonVisibility(0); // Initialize button visibility
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(ExamActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ApiError", t.getMessage(), t);
            }
        });
    }

    private void updateButtonVisibility(int position) {
        if (position == 0) {
            btnPrev.setVisibility(View.GONE);
            btnNext.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.GONE);  // Ensure btnSubmit is hidden
        } else if (position == questions.size() - 1) {
            btnNext.setVisibility(View.GONE);
            btnPrev.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.VISIBLE);  // Show btnSubmit when btnNext is gone
        } else {
            btnPrev.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.GONE);  // Ensure btnSubmit is hidden
        }
    }

    private void updateCountDownText() {
        int minutes = (int) ((timeLeftInMillisTotal / 1000) / 60);
        int seconds = (int) ((timeLeftInMillisTotal / 1000) % 60);
        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        cDown.setText(timeLeftFormatted);
        if (timeLeftInMillisTotal < 10000) {
            cDown.setTextColor(Color.RED);
        } else {
            cDown.setTextColor(Color.BLACK);
        }
    }

    private void submitExam() {
        int correctAnswers = calculateCorrectAnswers(); // Tính tổng số câu đúng
        int totalQuestions = questions.size();
        String timeTaken = getTimeTaken(); // Lấy thời gian làm bài

        // Kiểm tra kết quả ĐẠT hoặc TRƯỢT
        String passStatus = correctAnswers >= 21 ? "ĐẠT" : "TRƯỢT";

        // Tạo thông báo kết quả bao gồm số câu đúng và trạng thái
        String resultMessage = String.format("Đúng %d/%d câu.\nThời gian làm bài: %s\nKết quả: %s",
                correctAnswers, totalQuestions, timeTaken, passStatus);

        // Hiển thị dialog với kết quả
        new AlertDialog.Builder(this)
                .setTitle("Kết quả")
                .setMessage(resultMessage)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Quay trở lại ExamListActivity sau khi bấm OK
                        Intent intent = new Intent(ExamActivity.this, ExamListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }

    private int calculateCorrectAnswers() {
        int correctAnswers = 0;
        for (Question question : questions) {
            if (question.isCorrect()) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    private String getTimeTaken() {
        int minutes = (int) ((1140000 - timeLeftInMillisTotal) / 1000) / 60;
        int seconds = (int) ((1140000 - timeLeftInMillisTotal) / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
