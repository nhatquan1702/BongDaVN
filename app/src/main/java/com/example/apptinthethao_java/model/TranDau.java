package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TranDau {
    @SerializedName("match_id")
    @Expose
    private String match_id;
    @SerializedName("match_happen_time")
    @Expose
    private Date match_happen_time;
    @SerializedName("match_location")
    @Expose
    private String match_location;
    @SerializedName("clb_home_name")
    @Expose
    private String clb_home_name;
    @SerializedName("clb_guess_name")
    @Expose
    private String clb_guess_name;
    @SerializedName("match_result")
    @Expose
    private String match_result;
    @SerializedName("match_description")
    @Expose
    private String match_description;

    public TranDau() {
    }

    public TranDau(String match_id, Date match_happen_time, String match_location, String clb_home_name, String clb_guess_name, String match_result, String match_description) {
        this.match_id = match_id;
        this.match_happen_time = match_happen_time;
        this.match_location = match_location;
        this.clb_home_name = clb_home_name;
        this.clb_guess_name = clb_guess_name;
        this.match_result = match_result;
        this.match_description = match_description;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public Date getMatch_happen_time() {
        return match_happen_time;
    }

    public void setMatch_happen_time(Date match_happen_time) {
        this.match_happen_time = match_happen_time;
    }

    public String getMatch_location() {
        return match_location;
    }

    public void setMatch_location(String match_location) {
        this.match_location = match_location;
    }

    public String getClb_home_name() {
        return clb_home_name;
    }

    public void setClb_home_name(String clb_home_name) {
        this.clb_home_name = clb_home_name;
    }

    public String getClb_guess_name() {
        return clb_guess_name;
    }

    public void setClb_guess_name(String clb_guess_name) {
        this.clb_guess_name = clb_guess_name;
    }

    public String getMatch_result() {
        return match_result;
    }

    public void setMatch_result(String match_result) {
        this.match_result = match_result;
    }

    public String getMatch_description() {
        return match_description;
    }

    public void setMatch_description(String match_description) {
        this.match_description = match_description;
    }
}
