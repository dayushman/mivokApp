package com.example.mivok;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.mivok.AndroidAdaptor.afChangeListener;
import static com.example.mivok.AndroidAdaptor.audioManager;

public class colors_activity extends AppCompatActivity {
    MediaPlayer md;
    @Override
    protected void onStop() {
        super.onStop();
        md=AndroidAdaptor.getMd();
        if(md!=null){
            md.release();
            md=null;
        }
        audioManager.abandonAudioFocus(afChangeListener);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ArrayList<Word> words=new ArrayList<>();
        words.add(new Word("wetetti","Red",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("chokokki","Green",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("takaakki","Brown",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("topoppi","Gray",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("kululli","Black",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("kelelli","White",R.drawable.color_white,R.raw.color_white));
        words.add(new Word("topiise","Dusky Yellow",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("chiwiite","Mustard Yellow",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));


        AndroidAdaptor itemsAdapter=new AndroidAdaptor(this,words,getColor(R.color.category_colors));
        ListView listView = findViewById(R.id.gridView);
        md=itemsAdapter.getMd();

        listView.setAdapter(itemsAdapter);
    }
}