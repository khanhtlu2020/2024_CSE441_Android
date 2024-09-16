package com.example.project01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//public class MainActivity extends AppCompatActivity implements View.OnclickListener
public class MainActivity extends AppCompatActivity {
    private TextView txt_helloandroid;
//    private Button btn_clickme;
    private Button btnOpenSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //B1: Tham chieu phan tu se tac dong tren Activity
        txt_helloandroid = (TextView) findViewById(R.id.txt_helloandroid);
//        btn_clickme = (Button) findViewById(R.id.btn_open_main);
        btnOpenSecond = (Button) findViewById(R.id.btn_open_main);

        //B2: Xu ly su kien
        btnOpenSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Open Second", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(myIntent);
            }
        });
    }


}