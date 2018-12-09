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

public class TabAlbum extends Fragment {
    View view;
    ListView listView;
    ArrayList<Song> list;
    SongAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_album,container,false);
        listView = view.findViewById(R.id.lvalbum);
        list = new ArrayList<>();

        list.add(new Song("Ca sĩ Ưng Hoàng phúc","abcde","Nhạc Trẻ","7 Ca khúc"));
        list.add(new Song("Acoustic","abcde","Nhạc Trẻ","3 Ca khúc"));
        list.add(new Song("Nhạc Vàng","abcde","Nhạc Trẻ","19 Ca khúc"));
        list.add(new Song("Album Buồn của anh","abcde","Nhạc Trẻ","13 Ca khúc"));
        list.add(new Song("Album Em chưa 18","abcde","Nhạc Trẻ","6 Ca khúc"));
        list.add(new Song("Album Thuyền và Biển","abcde","Nhạc Trẻ","17 Ca khúc"));
        list.add(new Song("Album Nhạc trẻ tháng 11","abcde","Nhạc Trẻ","47 Ca khúc"));
        list.add(new Song("Album Những ca khúc để đời","abcde","Nhạc Trẻ","20 Ca khúc"));
        list.add(new Song("Album Bolero","abcde","Nhạc Trẻ","43 Ca khúc"));

        adapter = new SongAdapter(getContext(),list);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        return view;

    }
}
