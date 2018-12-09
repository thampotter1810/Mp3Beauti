package com.example.potter.mp3beauty;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabLayout tbl;
    ViewPager viewPager;
    AdapterFragment adpater;
    ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        addTitle();
    }

    private void addTitle() {
        adpater = new AdapterFragment(getSupportFragmentManager(),list);
        viewPager.setAdapter(adpater);
        tbl.setupWithViewPager(viewPager);

        for (int i = 0; i < tbl.getTabCount(); i++){
            switch (i){
                case 0:
                    tbl.getTabAt(i).setText("Thư mục");
                    break;
                case 1:
                    tbl.getTabAt(i).setText("Bài Hát");
                    break;

                case 2:
                    tbl.getTabAt(i).setText("PlayList");
                    break;
                case 3:
                    tbl.getTabAt(i).setText("Album");
                    break;
                case 4:
                    tbl.getTabAt(i).setText("Ca sĩ");
                    break;
            }

        }
    }

    private void anhXa() {

        tbl = findViewById(R.id.TableLayoutTitle);
        viewPager = findViewById(R.id.ViewPager);
        list = new ArrayList<>();
        list.add(new TabThuMuc());
        list.add(new TabBaiHat());
        list.add(new TabPlayList());
        list.add(new TabAlbum());
        list.add(new TabCaSi());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
