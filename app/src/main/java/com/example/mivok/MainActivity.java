package com.example.mivok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView number= (TextView) findViewById(R.id.numbers);
        number.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent open= new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(open);
            }
        });


        TextView family= (TextView) findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent open= new Intent(MainActivity.this,family_activity.class);
                startActivity(open);
            }
        });


        TextView phrases= (TextView) findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent open= new Intent(MainActivity.this,phrases_activity.class);
                startActivity(open);
            }
        });


        TextView colors= (TextView) findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent open= new Intent(MainActivity.this,colors_activity.class);
                startActivity(open);
            }
        });
    }

}