package com.example.drivingtestapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;

public class LawListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button btnNghiDinh, btnHieuLenh, btnDungXe, btnTocDo, btnDuongCam;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_list);

        btnNghiDinh = findViewById(R.id.btnNongDoCon);
        btnHieuLenh = findViewById(R.id.btnHieuLenh);
        btnDungXe = findViewById(R.id.btnDungXe);
        btnTocDo = findViewById(R.id.btnTocDo);
        btnDuongCam = findViewById(R.id.btnDuongCam);

        btnNghiDinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LawListActivity.this, LawActivity.class);
                startActivity(intent);
            }
        });

        btnHieuLenh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LawListActivity.this, LawActivity1.class);
                startActivity(intent);
            }
        });

        btnDungXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LawListActivity.this, LawActivity2.class);
                startActivity(intent);
            }
        });

        btnTocDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LawListActivity.this, LawActivity3.class);
                startActivity(intent);
            }
        });

        btnDuongCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LawListActivity.this, LawActivity4.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar_lawlist);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_lawlist);
        NavigationView navigationView = findViewById(R.id.nav_view_lawlist);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_main) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.nav_thithusathach) {
            startActivity(new Intent(this, ExamListActivity.class));
        } else if (id == R.id.nav_hoclythuyet) {
            startActivity(new Intent(this, LearningActivity.class));
        } else if (id == R.id.nav_bienbao) {
            startActivity(new Intent(this, RoadSignActivity.class));
        } else if (id == R.id.nav_tracuuluat) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if (id == R.id.nav_thoat) {
            exit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void exit() {
        finishAffinity();
        System.exit(0);
    }

}
