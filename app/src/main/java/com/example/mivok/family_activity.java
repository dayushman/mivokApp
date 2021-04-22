package com.example.mivok;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.mivok.AndroidAdaptor.afChangeListener;
import static com.example.mivok.AndroidAdaptor.audioManager;

public class family_activity extends AppCompatActivity {

    MediaPlayer md;
    @Override
    protected void onStop() {
        super.onStop();
        md=AndroidAdaptor.getMd();
        if(md!=null){
            md.release();
            md=null;
            audioManager.abandonAudioFocus(afChangeListener);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ArrayList<Word> family=new ArrayList<>();
        family.add(new Word("epe","father",R.drawable.family_father,R.raw.family_father));
        family.add(new Word("eta","mother",R.drawable.family_mother,R.raw.family_mother));
        family.add(new Word("angsi","son",R.drawable.family_son,R.raw.family_son));
        family.add(new Word("tune","daughter",R.drawable.family_daughter,R.raw.family_daughter));
        family.add(new Word("taachi","older brother",R.drawable.family_older_brother,R.raw.family_older_brother));
        family.add(new Word("chalitti","younger brother",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family.add(new Word("tete","older sister",R.drawable.family_older_sister,R.raw.family_older_sister));
        family.add(new Word("ama","grandmother",R.drawable.family_grandmother,R.raw.family_grandmother));
        family.add(new Word("paapa","grandfather",R.drawable.family_grandfather,R.raw.family_grandfather));

        AndroidAdaptor adaptor=new AndroidAdaptor(this,family,getColor(R.color.category_family));

        ListView list=findViewById(R.id.gridView);
        list.setAdapter(adaptor);
    }
}