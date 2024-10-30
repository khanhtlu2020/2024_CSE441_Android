package com.example.drivingtestapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button btnExam, btnMore;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExam = findViewById(R.id.btnExam);
        btnMore = findViewById(R.id.btnMore);

        btnExam.setOnClickListener(new View.OnClickListener() {
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

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_main) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_thithusathach) {
            startActivity(new Intent(this, ExamListActivity.class));
        }
//        else if (id == R.id.nav_hoclythuyet) {
//            startActivity(new Intent(this, TraDacBietActivity.class));
//        } else if (id == R.id.nav_tracuuluat) {
//            startActivity(new Intent(this, BanhNgotActivity.class));
//        } else if (id == R.id.nav_bienbao) {
//            startActivity(new Intent(this, BanhManActivity.class));
//        } else if (id == R.id.nav_tracuuluat) {
//            startActivity(new Intent(this, CaiDatActivity.class));
//        }
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
