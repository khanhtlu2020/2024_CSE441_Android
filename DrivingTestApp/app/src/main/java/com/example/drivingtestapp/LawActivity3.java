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

public class LawActivity3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerViewLuat;
    ArrayList<Law> itemList;
    LawAdapter adapter;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law3);

        Toolbar toolbar = findViewById(R.id.toolbar_tocdo);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_tocdo);
        NavigationView navigationView = findViewById(R.id.nav_view_tocdo);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        recyclerViewLuat = findViewById(R.id.recyclerView_tocdo);
        itemList = new ArrayList<>();
        loadItemsFromJson();
        recyclerViewLuat.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LawAdapter(this, R.layout.law_list, itemList);
        recyclerViewLuat.setAdapter(adapter);
    }

    private void loadItemsFromJson() {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("law.json");
            String jsonStr = convertStreamToString(inputStream);
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObj.getJSONArray("tocdo");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                String tenLuat = itemObj.getString("TenLuat");
                String mucPhat = itemObj.getString("MucPhat");

                itemList.add(new Law(tenLuat, mucPhat));
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
            startActivity(new Intent(this, LearningActivity.class));
        } else if (id == R.id.nav_bienbao) {
            startActivity(new Intent(this, RoadSignActivity.class));
        } else if (id == R.id.nav_tracuuluat) {
            startActivity(new Intent(this, LawListActivity.class));
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
