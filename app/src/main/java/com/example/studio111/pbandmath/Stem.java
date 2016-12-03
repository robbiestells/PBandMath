package com.example.studio111.pbandmath;

/**
 * Created by rsteller on 12/2/2016.
 */

public class Stem {
    private String stem;

    public Stem(){}

    public Stem(String stem){
        this.stem = stem;
    }

    public String getText(){
        return stem;
    }

    public void setText(String stem){
        this.stem = stem;
    }
}
