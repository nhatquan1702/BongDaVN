package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("account_email")
    @Expose
    private String account_email;

    @SerializedName("account_password")
    @Expose
    private String account_password;

    @SerializedName("role")
    @Expose
    private int role;

    public User(String account_email, String account_password, int role) {
        this.account_email = account_email;
        this.account_password = account_password;
        this.role = role;
    }

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getAccount_password() {
        return account_password;
    }

    public void setAccount_password(String account_password) {
        this.account_password = account_password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
