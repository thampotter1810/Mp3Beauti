package com.example.potter.mp3beauty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends BaseAdapter {


    Context context;
    ArrayList<Song> list;

    public SongAdapter(Context context, ArrayList<Song> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_song,null);

        TextView tvName = view.findViewById(R.id.textViewName);
        TextView tvArtist = view.findViewById(R.id.textViewArtist);



        Song song = list.get(position);
        tvArtist.setText(song.getSongArtits());
        tvName.setText(song.getSongName());

        return view;
    }

    private class holder{
        ImageView imgAvatar;
        TextView tvName;
        TextView tvArtist;
    }
}
