package com.example.rattaggingstudio;

public class Info {
    public String title;
    public String date;
    public Info(String addTitle, String addDate){
        this.title = addTitle;
        this.date = addDate;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDate()
    {
        return this.date;
    }
}
