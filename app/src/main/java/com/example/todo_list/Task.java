package com.example.todo_list;

import java.util.ArrayList;

public class Task {
    private String topic;
    private String content;
    private String date;
    private boolean checkOnList;

    public Task() {
        this.checkOnList = false;
    }

    public Task(String topic, String content, String date) {
        this.topic = topic;
        this.content = content;
        this.date = date;
        this.checkOnList = false;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCheckOnList() {
        return checkOnList;
    }

    public void setCheckOnList(boolean checkOnList) {
        this.checkOnList = checkOnList;
    }

    public static ArrayList<Task> getTasks() {
        ArrayList<Task> result = new ArrayList<>();
        result.add(new Task("TopicExample", "ContentExample", "22.01.2022"));

        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
