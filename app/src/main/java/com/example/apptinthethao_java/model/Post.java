package com.example.apptinthethao_java.model;

public class Post {
    private int post_id = 0;

    public Post(int post_id, String post_title, String post_img, String post_create_time) {
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_img = post_img;
        this.post_create_time = post_create_time;
    }

    private String post_title = "";
    private String post_img = "";
    private String post_create_time = "";


    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_img() {
        return post_img;
    }

    public void setPost_img(String post_img) {
        this.post_img = post_img;
    }

    public String getPost_create_time() {
        return post_create_time;
    }

    public void setPost_create_time(String post_create_time) {
        this.post_create_time = post_create_time;
    }


}
