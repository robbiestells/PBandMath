package com.example.studio111.pbandmath;

/**
 * Created by rsteller on 12/2/2016.
 */

public class Stem {
    private String stems;
    private String id;
    private String cat;
    private String grade;

    public Stem(){}

    public Stem(String grade, String cat, String id, String stems){
        this.cat = cat;
        this.grade = grade;
        this.stems = stems;
        this.id = id;
    }

    public String getStem(){
        return stems;
    }

    public void setStem(String stems){
        this.stems = stems;
    }

    public String getId(){return id;}

    public void setId(String id){this.id = id;}

    public String getCategory(){return cat;}

    public void setCategory(String other){this.cat = cat;}

    public String getGrade(){return grade;}

    public void setGrade(String grade){this.grade = grade;}
}
