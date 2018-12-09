package com.example.potter.mp3beauty;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class AdapterPlayList extends BaseAdapter {
    Context context;
    ArrayList<Song> songs;

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        return view;
    }
}
