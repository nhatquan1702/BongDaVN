package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Analysis {
    @SerializedName("count_account")
    @Expose
    private int count_account;

    @SerializedName("count_post")
    @Expose
    private int count_post;

    @SerializedName("count_match")
    @Expose
    private int count_match;

    public Analysis(int count_account, int count_post, int count_match) {
        this.count_account = count_account;
        this.count_post = count_post;
        this.count_match = count_match;
    }

    public int getCount_account() {
        return count_account;
    }

    public void setCount_account(int count_account) {
        this.count_account = count_account;
    }

    public int getCount_post() {
        return count_post;
    }

    public void setCount_post(int count_post) {
        this.count_post = count_post;
    }

    public int getCount_match() {
        return count_match;
    }

    public void setCount_match(int count_match) {
        this.count_match = count_match;
    }
}
