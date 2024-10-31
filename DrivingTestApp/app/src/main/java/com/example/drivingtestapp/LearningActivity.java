package com.example.drivingtestapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LearningActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerViewLearning;
    ArrayList<Learning> itemList;
    LearningAdapter adapter;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        Toolbar toolbar = findViewById(R.id.toolbar_learning);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_learning);
        NavigationView navigationView = findViewById(R.id.nav_view_thi);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        recyclerViewLearning = findViewById(R.id.recyclerView_learning);
        itemList = new ArrayList<>();
        loadItemsFromJson();
        recyclerViewLearning.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LearningAdapter(this, R.layout.learning_list, itemList);
        recyclerViewLearning.setAdapter(adapter);
    }

    private void loadItemsFromJson() {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("learning.json");
            String jsonStr = convertStreamToString(inputStream);
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObj.getJSONArray("hoclythuyet");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                String maCauHoi = itemObj.getString("MaCauHoi");
                String cauHoi = itemObj.getString("CauHoi");
                String dapAn = itemObj.getString("DapAn");

                itemList.add(new Learning(maCauHoi, cauHoi, dapAn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.nav_thithusathach) {
            startActivity(new Intent(this, ExamListActivity.class));
        } else if (id == R.id.nav_hoclythuyet) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_bienbao) {
            startActivity(new Intent(this, RoadSignActivity.class));
        }
//        else if (id == R.id.nav_tracuuluat) {
//            startActivity(new Intent(this, BanhManActivity.class));
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
