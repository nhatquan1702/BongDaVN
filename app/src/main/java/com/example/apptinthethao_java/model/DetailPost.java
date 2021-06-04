package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailPost {
    @SerializedName("post_id")
    @Expose
    private int post_id = 0;

    @SerializedName("post_title")
    @Expose
    private String post_title = "";

    @SerializedName("post_content")
    @Expose
    private String post_content = "";

    @SerializedName("post_img")
    @Expose
    private String post_img = "";

    @SerializedName("post_create_time")
    @Expose
    private String post_create_time = "";

    @SerializedName("post_create_by")
    @Expose
    private String post_create_by = "";

    @SerializedName("post_status")
    @Expose
    private int post_status = 1;

    @SerializedName("post_view")
    @Expose
    private int post_view = 1;

    public DetailPost(int post_id, String post_title, String post_content,
                      String post_img, String post_create_time,
                      String post_create_by, int post_status, int post_view) {
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_img = post_img;
        this.post_create_time = post_create_time;
        this.post_create_by = post_create_by;
        this.post_status = post_status;
        this.post_view = post_view;
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

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
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

    public int getPost_status() {
        return post_status;
    }

    public void setPost_status(int post_status) {
        this.post_status = post_status;
    }

    public int getPost_view() {
        return post_view;
    }

    public void setPost_view(int post_view) {
        this.post_view = post_view;
    }
}
