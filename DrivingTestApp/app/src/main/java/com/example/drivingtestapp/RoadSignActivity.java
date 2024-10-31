package com.example.drivingtestapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TabHost;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public class RoadSignActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Declare UI elements
    private RecyclerView rv1, rv2, rv3;
    private TabHost tab;
    private DrawerLayout drawerLayout;

    // Declare data structures
    private ArrayList<RoadSign> list1, list2, list3;
    private RoadSignAdapter myarray1, myarray2, myarray3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_sign);

        // Initialize UI elements
        addControl();

        // Set tab change listener
        addEvent();

        Toolbar toolbar = findViewById(R.id.toolbar_sign);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_sign);
        NavigationView navigationView = findViewById(R.id.nav_view_sign);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Load initial data for all tabs
        loadItemsFromJson("bienbaocam"); // Load data for the first tab by default
    }

    private void loadItemsFromJson(String tabName) {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("sign.json");
            String jsonStr = convertStreamToString(inputStream);
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObj.getJSONArray(tabName);

            ArrayList<RoadSign> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                String tenBienBao = itemObj.getString("TenBienBao");
                String noiDungBienBao = itemObj.getString("NoidungBienBao");
                String hinh = itemObj.getString("Hinh");
                list.add(new RoadSign(tenBienBao, noiDungBienBao, hinh));
            }
            updateList(tabName, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    private void updateList(String tabName, ArrayList<RoadSign> list) {
        if (tabName.equalsIgnoreCase("bienbaocam")) {
            list1.clear();
            list1.addAll(list);
            myarray1.notifyDataSetChanged();
        } else if (tabName.equalsIgnoreCase("bienbaonguyhiem")) {
            list2.clear();
            list2.addAll(list);
            myarray2.notifyDataSetChanged();
        } else if (tabName.equalsIgnoreCase("bienbaohieulenh")) {
            list3.clear();
            list3.addAll(list);
            myarray3.notifyDataSetChanged();
        }
    }

    private void addEvent() {
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1")) {
                    loadItemsFromJson("bienbaocam");
                } else if (tabId.equalsIgnoreCase("t2")) {
                    loadItemsFromJson("bienbaonguyhiem");
                } else if (tabId.equalsIgnoreCase("t3")) {
                    loadItemsFromJson("bienbaohieulenh");
                }
            }
        });
    }

    private void addControl() {
        // Find and initialize UI elements
        tab = (TabHost) findViewById(R.id.tabhost);
        tab.setup();

        // Create and configure tabs
        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Biển báo cấm");
        tab.addTab(tab1);

        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Biển báo nguy hiểm");
        tab.addTab(tab2);

        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("Biển báo hiệu lệnh");
        tab.addTab(tab3);

        rv1 = (RecyclerView) findViewById(R.id.rv1);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        rv2 = (RecyclerView) findViewById(R.id.rv2);
        rv2.setLayoutManager(new LinearLayoutManager(this));
        rv3 = (RecyclerView) findViewById(R.id.rv3);
        rv3.setLayoutManager(new LinearLayoutManager(this));

        // Initialize data structures
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();

        // Initialize adapters
        myarray1 = new RoadSignAdapter(this, R.layout.roadsign_list, list1);
        myarray2 = new RoadSignAdapter(this, R.layout.roadsign_list, list2);
        myarray3 = new RoadSignAdapter(this, R.layout.roadsign_list, list3);

        // Set adapters to RecyclerViews
        rv1.setAdapter(myarray1);
        rv2.setAdapter(myarray2);
        rv3.setAdapter(myarray3);
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
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_thoat) {
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
