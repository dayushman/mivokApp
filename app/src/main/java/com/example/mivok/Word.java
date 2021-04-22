package com.example.mivok;

public class Word {
    private String mMivokTranslation,mDefaultTranslation;
    private int image_resource,audio_resource;

    public Word(String mvok,String def,int aud){
        mMivokTranslation=mvok;
        mDefaultTranslation=def;
        image_resource=-1;
        audio_resource=aud;
    }

    public Word(String mvok,String def,int img,int aud){
        mMivokTranslation=mvok;
        mDefaultTranslation=def;
        image_resource=img;
        audio_resource=aud;
    }

    public int getAudio_resource() { return audio_resource; }

    public int getImage_resource() {
        return image_resource;
    }

    public String getMivokTranslation(){
        return this.mMivokTranslation;
    }
    public String getDefaultTranslation(){
        return this.mDefaultTranslation;
    }
}
