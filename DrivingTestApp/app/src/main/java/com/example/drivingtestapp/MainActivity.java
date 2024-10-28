package com.example.drivingtestapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drivingtestapp.models.DataBase;

public class MainActivity extends AppCompatActivity {
//    private ArrayList<Question> questions = new ArrayList<>();
//    DataBase dataBase = new DataBase(this,null,null,1);

    Button btnStart,btnTrain,btnHistory,btnMore, btnThithu;
    TextView txtRs,txtRsMax;

    DataBase dataBase;

    int userId;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        Intent data = o.getData();
                        int rs = data.getIntExtra("result",0);
                        txtRs.setText("Result: "+rs);
                        dataBase.QuerySetData("insert into results(user_id,result) values("+userId+","+rs+")");
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map();

        // Gọi database
        dataBase = new DataBase(this, "user.sqlite", null, 1);

        // Lấy dữ liệu từ người dùng đăng nhập
        getUserData();

        showResult();

        btnThithu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExamListActivity.class);
                startActivity(intent);
            }
        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://onthilaixe.vn/"));
                startActivity(intent);
            }
        });
    }

    private void showResult() {
        // Tạo bảng lưu kết quả
        String createTBUser = "create table if not exists results (id integer primary key autoincrement, user_id integer REFERENCES users(id), result integer)";
        dataBase.QuerySetData(createTBUser);

        // Lấy dữ liệu từ bảng lưu kết quả
        String getDataUser = "select * from results where user_id = "+userId;
        Cursor cursor = dataBase.QueryGetData(getDataUser);
        int maxRs = 0;
        while (cursor.moveToNext()) {
            int result = cursor.getInt(2);
            if(result>maxRs){
                maxRs = result;
            }
        }
        txtRsMax.setText("Max result: "+maxRs);
    }

    private void getUserData() {
        Intent intent = this.getIntent();
        userId = intent.getIntExtra("user_id",0);
        Toast.makeText(this, userId+"", Toast.LENGTH_SHORT).show();
    }


    private void map(){
        btnThithu = (Button) findViewById(R.id.btnThithu);
        btnStart = (Button) findViewById(R.id.start);
        txtRs = (TextView) findViewById(R.id.txtRs);
        txtRsMax = (TextView) findViewById(R.id.txtRsMax);
        btnTrain = (Button) findViewById(R.id.btnTrain);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        btnMore = (Button) findViewById(R.id.btnMore);
    }

}