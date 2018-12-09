package com.example.potter.mp3beauty;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class TabPlayList extends Fragment {

    ListView lv;
    ArrayList<Song> list;
    SongAdapter adapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.tab_play_list,container,false);
        anhXa();

        list = new ArrayList<>();
        list.add(new Song("Nhạc trẻ hay nhất","abcde","Nhạc Trẻ","12 Ca khúc"));
        list.add(new Song("Top 100 ca khúc trẻ","abcde","Nhạc Trẻ","5 Ca khúc"));
        list.add(new Song("Hay nghe nhiều","abcde","Nhạc Trẻ","54 Ca khúc"));
        list.add(new Song("Nhạc Thiếu Nhi","abcde","Nhạc Trẻ","4 Ca khúc"));

        adapter = new SongAdapter(getContext(),list);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
        return view;
    }

    private void anhXa() {
        lv = view.findViewById(R.id.lvplaylist);
    }
}
