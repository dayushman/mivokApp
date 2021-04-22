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

public class NumbersActivity extends AppCompatActivity {
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

        ArrayList<Word> words=new ArrayList<>();
        words.add(new Word("lugi","One",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("otiiko","Two",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("tolookosu","Three",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("oyyisa","Four",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("massokka","Five",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("temmokka","Six",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("kenekaku","Seven",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("kawinta","Eight",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("wo'e","nine",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("na'aacha","ten",R.drawable.number_ten,R.raw.number_ten));


        AndroidAdaptor itemsAdapter=new AndroidAdaptor(this,words,getColor(R.color.category_numbers));
        ListView listView = findViewById(R.id.gridView);
        md = itemsAdapter.getMd();
        listView.setAdapter(itemsAdapter);
    }
}