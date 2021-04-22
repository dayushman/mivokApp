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

public class phrases_activity extends AppCompatActivity {

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
        family.add(new Word("minto wuksus","Where are you going?",R.raw.phrase_where_are_you_going));
        family.add(new Word("tinne ayaasne","What is your name?",R.raw.phrase_what_is_your_name));
        family.add(new Word("ayaaset...","My name is...",R.raw.phrase_my_name_is));
        family.add(new Word("michekses?","How are you feeling.",R.raw.phrase_how_are_you_feeling));
        family.add(new Word("kuchi achit","I'm feeling good.",R.raw.phrase_im_feeling_good));
        family.add(new Word("eenes'aa?","Are you coming?",R.raw.phrase_are_you_coming));
        family.add(new Word("hee'eenem","Yes, I'm coming",R.raw.phrase_yes_im_coming));
        family.add(new Word("eenem","I'm coming.",R.raw.phrase_im_coming));
        family.add(new Word("yaawutis","Let's go.",R.raw.phrase_lets_go));
        family.add(new Word("enni'nem","Come here.",R.raw.phrase_lets_go));

        AndroidAdaptor adaptor=new AndroidAdaptor(this,family,getColor(R.color.category_phrases));

        ListView list=findViewById(R.id.gridView);
        md = adaptor.getMd();
        list.setAdapter(adaptor);
    }
}