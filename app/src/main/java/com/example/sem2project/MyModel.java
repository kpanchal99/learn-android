package com.example.sem2project;

public class MyModel {
    String title = "";
//    String activityName = "";
    Class activityName = null;
    public MyModel(String title, Class activityName) {
        this.title = title;
        this.activityName = activityName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Class getActivityName() {
        return activityName;
    }

    public void setActivityName(String age) {
        this.activityName = activityName;
    }
}
