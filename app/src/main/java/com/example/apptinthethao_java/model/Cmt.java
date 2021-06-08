package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cmt {
    public String getId_post() {
        return id_post;
    }

    public void setId_post(String id_post) {
        this.id_post = id_post;
    }

    @SerializedName("post_id")
    @Expose
    private String id_post;
    @SerializedName("comment_by")
    @Expose
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNoiDungCmt() {
        return noiDungCmt;
    }

    public void setNoiDungCmt(String noiDungCmt) {
        this.noiDungCmt = noiDungCmt;
    }

    public String getNgayCmt() {
        return ngayCmt;
    }

    public void setNgayCmt(String ngayCmt) {
        this.ngayCmt = ngayCmt;
    }
    @SerializedName("comment_content")
    @Expose
    private String noiDungCmt;
    @SerializedName("comment_time")
    @Expose
    private String ngayCmt;

    public Cmt(String id, String userName, String noiDungCmt, String ngayCmt) {
        this.id_post = id;
        this.userName = userName;
        this.noiDungCmt = noiDungCmt;
        this.ngayCmt = ngayCmt;
    }
}
