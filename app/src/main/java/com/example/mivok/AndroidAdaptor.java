package com.example.mivok;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mivok.R;
import com.example.mivok.Word;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
import static android.media.AudioManager.STREAM_MUSIC;

public class AndroidAdaptor extends ArrayAdapter<Word> {
    private int color;
    static MediaPlayer md;
    static AudioManager audioManager;
    static AudioManager.OnAudioFocusChangeListener afChangeListener;


    public AndroidAdaptor(@NonNull Context context, ArrayList<Word> word,int color) {
        super(context,0 , word);
        this.color=color;
    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        final Word currentWord = getItem(position);

//        setting up default meaning
        TextView defMeaning=listItemView.findViewById(R.id.textview1);
        assert currentWord != null;
        defMeaning.setText(currentWord.getDefaultTranslation());

//        setting up images
        listItemView.findViewById(R.id.text_holder).setBackgroundColor(color);




//        setting up the on click listener
        listItemView.findViewById(R.id.text_holder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(md!=null){
                    md.release();
                    md=null;
                }




//                Requesting Audio FOcus
                int res=isRequestGranted(getContext());
                if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    md= MediaPlayer.create(getContext(),currentWord.getAudio_resource());
                    md.start();
                }

            }
        });
        if (md!=null){
            md.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    md.release();
                    md=null;
                    audioManager.abandonAudioFocus(afChangeListener);

                }
            });
        }

        TextView mivMeaning=listItemView.findViewById(R.id.textview2);
        mivMeaning.setText(currentWord.getMivokTranslation());

        if(currentWord.getImage_resource()!=-1){
            ImageView img=listItemView.findViewById(R.id.icon);
            img.setImageResource(currentWord.getImage_resource());
        }
        else
            listItemView.findViewById(R.id.icon).setVisibility(View.GONE);
        return listItemView;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public static int isRequestGranted(Context context){
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        afChangeListener =
                new AudioManager.OnAudioFocusChangeListener() {
                    public void onAudioFocusChange(int focusChange) {
                        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                            // Pause playback because your Audio Focus was
                            // temporarily stolen, but will be back soon.
                            // i.e. for a phone call
                            if(md!=null){
                                md.pause();
                                md.seekTo(0);
                            }

                        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                            // Stop playback, because you lost the Audio Focus.
                            // i.e. the user started some other playback app
                            // Remember to unregister your controls/buttons here.
                            // And release the kra — Audio Focus!
                            // You’re done.
                            if(md!=null){
                                md.release();
                                md=null;
                            }
                            audioManager.abandonAudioFocus(afChangeListener);
                        }
                        else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                            // Resume playback, because you hold the Audio Focus
                            // again!
                            // i.e. the phone call ended or the nav directions
                            // are finished
                            // If you implement ducking and lower the volume, be
                            // sure to return it to normal here, as well.
                            /*if(md!=null){
                                md.start();
                            }*/
                        }
                    }
                };
        int res = audioManager.requestAudioFocus(afChangeListener, STREAM_MUSIC, AUDIOFOCUS_GAIN_TRANSIENT );
        return res;
    }














    public static MediaPlayer getMd() {
        return md;
    }
}
