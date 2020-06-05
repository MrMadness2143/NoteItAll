package com.example.noteitall;

public class Note {
    private long ID;
    private String title;
    private String importance;
    private String type;
    private String content;
    private String favourite;
    private String date;
    private String time;

    Note(){}
    Note(String title, String importance, String type, String content, String favourite,
         String date, String time){     //constructor for creating new note
        this.title = title;
        this.importance = importance;
        this.type = type;
        this.content = content;
        this.favourite = favourite;
        this. date = date;
        this.time = time;
    }
    Note(long ID, String title, String importance, String type, String content, String favourite,
         String date, String time){     //constructor for getting notes
        this.ID = ID;
        this.title = title;
        this.importance = importance;
        this.type = type;
        this.content = content;
        this.favourite = favourite;
        this.date = date;
        this.time = time;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
