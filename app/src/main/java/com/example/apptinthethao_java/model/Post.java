package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("post_id")
    @Expose
    private int post_id = 0;
    @SerializedName("post_title")
    @Expose
    private String post_title = "";
    @SerializedName("post_img")
    @Expose
    private String post_img = "";
    @SerializedName("post_create_time")
    @Expose
    private String post_create_time = "";

    @SerializedName("post_create_by")
    @Expose
    private String post_create_by = "";
    @SerializedName("post_content")
    @Expose
    private String post_content;

    public Post(){}

    public Post(int post_id, String post_title, String post_img, String post_create_time) {
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_img = post_img;
        this.post_create_time = post_create_time;
    }

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

    public String getPost_create_by() {
        return post_create_by;
    }

    public void setPost_create_by(String post_create_by) {
        this.post_create_by = post_create_by;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }
}
