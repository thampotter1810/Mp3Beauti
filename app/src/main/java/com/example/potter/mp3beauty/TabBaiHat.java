package com.example.potter.mp3beauty;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.potter.mp3beauty.MusicPlayer.PLAYER_PAUSE;
import static com.example.potter.mp3beauty.MusicPlayer.PLAYER_PLAY;

public class TabBaiHat extends Fragment implements AdapterView.OnItemClickListener {

    ListView lvSong;
    SongAdapter adapter;
    ArrayList<Song> list;
    Database db;
    SQLiteDatabase database;
    ImageView imgPlay, imgPause, imgNext, imgPrev, imgRepeat, imgShuffle;
    SeekBar sbProcess;
    TextView tvTitle, tvArtist, tvTimeprocess, tvTimeTotal;
    MusicPlayer musicPlayer;
    boolean isRunning;
    public static int UPDATE_TIME = 1;
    int vitri;
    int trangThai;
    private View viewFrg;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFrg =  inflater.inflate(R.layout.tab_bai_hat,container,false);

        db = new Database(getActivity());

        try {
            Bundle myBundle = getArguments();

            this.trangThai = myBundle.getInt("state");
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        Log.e("TRANGTHAI", String.valueOf(trangThai));

        initPermission();
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        anhXa();

        //database = db.getWritableDatabase();
       /* Cursor cs = database.rawQuery("select * from baihat",null);
        cs.moveToFirst();
        Log.d("Count",String.valueOf(cs.getCount()));
        Toast.makeText(MainActivity.this,cs.getString(2),Toast.LENGTH_SHORT).show();
        Log.d("Count",String.valueOf(cs.getCount()));*/

        addData();

        clickListener();
        return viewFrg;

    }


    private void anhXa() {
        lvSong = viewFrg.findViewById(R.id.LvSong);
        list = new ArrayList<>();
        adapter = new SongAdapter(getActivity(),list);
        lvSong.setAdapter(adapter);

        /*imgNext = viewFrg.findViewById(R.id.iv_next);
        imgPlay = viewFrg.findViewById(R.id.iv_play);
        imgPrev = viewFrg.findViewById(R.id.iv_previous);
        imgRepeat = viewFrg.findViewById(R.id.iv_repeat);
        imgShuffle = viewFrg.findViewById(R.id.iv_shuffle);

        tvArtist = viewFrg.findViewById(R.id.tv_artist);
        tvTitle = viewFrg.findViewById(R.id.tv_title);
        tvTimeprocess = viewFrg.findViewById(R.id.time_process);
        tvTimeTotal = viewFrg.findViewById(R.id.time_total);
        sbProcess = viewFrg.findViewById(R.id.sb_process);*/

        musicPlayer = new MusicPlayer();

    }


    private void addData(){
        database = db.getWritableDatabase();

        list.clear();
        Cursor cs = database.rawQuery("select * from baihat",null);

        for (int i = 0; i < cs.getCount(); i++){
            cs.moveToPosition(i);
            String name = cs.getString(2);
            String atist = cs.getString(4);
            String album = cs.getString(3);
            String path = cs.getString(1);

            list.add(new Song(name,path,album,atist));
        }

        adapter.notifyDataSetChanged();

    }

    private void clickListener() {
        lvSong.setOnItemClickListener(this);
        /*lvSong.setOnItemClickListener(this);
        imgShuffle.setOnClickListener(this);
        imgRepeat.setOnClickListener(this);
        imgPlay.setOnClickListener(this);
        imgPrev.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        sbProcess.setOnSeekBarChangeListener(this);
        musicPlayer.setOnComPletion(this);*/
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == UPDATE_TIME){
                tvTimeprocess.setText(getTimeFormat(musicPlayer.getTimeCurrent()));
                sbProcess.setProgress(musicPlayer.getTimeCurrent());
            }
        }
    };




    private String getTimeFormat(long time) {
        String tm = "";
        int h, s, m;
       /* String tmp = "";


        //chuyển thời gian sang đúng định dạng

        int s = (int) ((time % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        int m = (int) (time % (1000 * 60 * 60));
        int h = (int) (time / (1000 * 60 * 60));

        if (h > 0){
            tm = h + ":";
        }
        //thêm vào số 0 nếu có 1 chữ số;
        if (s < 10){
            s =
        }*/
        //giây
        s = (int) (time % 60);
        m = (int) ((time - s) / 60);
        if (m >= 60) {
            h = m / 60;
            m = m % 60;
            if (h > 0) {
                if (h < 10) {
                    tm += "0" + h + ":";
                } else
                    tm += h + ":";
            }
        }

        if (m < 10) {
            tm += "0" + m + ":";
        } else
            tm += m + ":";
        if(s < 10){
            tm += "0"+s;
        }else{
            tm += s;
        }
        return tm;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.vitri = position;

        //String path = list.get(i).getSongPath();
        Intent intent = new Intent(getActivity(), PlayMusic.class);

        Bundle bundle = new Bundle();
        // bundle.putString("lk",path);
        bundle.putInt("vitri",position);
        intent.putExtra("bainhac",bundle);

        if (trangThai == 0){
            musicPlayer.getPlayer();
            musicPlayer.stop();
            Objects.requireNonNull(getActivity()).stopService(intent);
        }

        startActivity(intent);

        //playMusic(i);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext(), "Permision Write File is Granted", Toast.LENGTH_SHORT).show();
                //caaps quyeen
                db.addSong();

            } else {
                Toast.makeText(getContext(), "Permision Write File is Denied", Toast.LENGTH_SHORT).show();
            }

        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void initPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){


                //permission don't granted
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Toast.makeText(getContext(), "Permission isn't granted ", Toast.LENGTH_SHORT).show();
                }
                // Permisson don't granted and dont show dialog again.
                else {
                    Toast.makeText(getContext(), "Permisson don't granted and dont show dialog again ", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        }
    }

}
