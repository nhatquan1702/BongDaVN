package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("account_email")
    @Expose
    private String account_email;

    @SerializedName("acccount_password")
    @Expose
    private String acccount_password;

    @SerializedName("role")
    @Expose
    private int role;

    public User(String account_email, String acccount_password, int role) {
        this.account_email = account_email;
        this.acccount_password = acccount_password;
        this.role = role;
    }

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getAcccount_password() {
        return acccount_password;
    }

    public void setAcccount_password(String acccount_password) {
        this.acccount_password = acccount_password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
