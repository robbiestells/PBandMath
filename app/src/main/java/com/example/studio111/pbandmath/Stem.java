package com.example.studio111.pbandmath;

import static android.R.attr.value;

/**
 * Created by rsteller on 12/2/2016.
 */

public class Stem {
    private String stem;
    private int id;
    private String category;
    private int grade;
    private String FIELD8;
    private String logic;
    private String ranges;
    private String solution;

    public Stem(){}

    public Stem(String FIELD8, String category, int grade, int id, String logic, String ranges, String solution, String stem){
        this.FIELD8 = FIELD8;
        this.category = category;
        this.grade = grade;
        this.id = id;
        this.logic = logic;
        this.ranges = ranges;
        this.solution = solution;
        this.stem = stem;

    }

    public String getStem(){
        return stem;
    }

    public void setStem(String stem){
        this.stem = stem;
    }

    public String getId(){return String.valueOf(id);}

    public void setId(int id){this.id = id;}

    public String getCategory(){return category;}

    public void setCategory(String category){this.category = category;}

    public String getGrade(){return String.valueOf(grade);}

    public void setGrade(int grade){this.grade = grade;}
}
