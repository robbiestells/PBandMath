package com.example.studio111.pbandmath;

/**
 * Created by rsteller on 12/2/2016.
 */

public class Stem {
    private String stem;
    private String id;
    private String other;

    public Stem(){}

    public Stem(String stem, String id, String other){
        this.stem = stem;
        this.id = id;
        this.other = other;
    }

    public String getStem(){
        return stem;
    }

    public void setStem(String stem){
        this.stem = stem;
    }

    public String getId(){return id;}

    public void setId(String id){this.id = id;}

    public String getOther(){return other;}

    public void setOther(String other){this.other = other;}
}
