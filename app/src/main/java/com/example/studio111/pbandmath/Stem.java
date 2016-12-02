package com.example.studio111.pbandmath;

/**
 * Created by rsteller on 12/2/2016.
 */

public class Stem {
    private String text;

    public Stem(){}

    public Stem(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }
}
