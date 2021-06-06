package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KetQua_TranDau {
    @SerializedName("match_id")
    @Expose
    private String matchID = "";

    @SerializedName("match_happen_time")
    @Expose
    private String matchHappenTime = "";

    @SerializedName("clb_home_name")
    @Expose
    private String clbHome = "";

    @SerializedName("clb_guess_name")
    @Expose
    private String clbGuess = "";

    @SerializedName("post_create_time")
    @Expose
    private String post_create_time = "";

    @SerializedName("h")
    @Expose
    private int tiSoHome = 0;

    @SerializedName("g")
    @Expose
    private int tiSoGuess = 0;

    public KetQua_TranDau() {
    }

    public KetQua_TranDau(String matchID, String matchHappenTime, String clbHome, String clbGuess, String post_create_time, int tiSoHome, int tiSoGuess) {
        this.matchID = matchID;
        this.matchHappenTime = matchHappenTime;
        this.clbHome = clbHome;
        this.clbGuess = clbGuess;
        this.post_create_time = post_create_time;
        this.tiSoHome = tiSoHome;
        this.tiSoGuess = tiSoGuess;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public String getMatchHappenTime() {
        return matchHappenTime;
    }

    public void setMatchHappenTime(String matchHappenTime) {
        this.matchHappenTime = matchHappenTime;
    }

    public String getClbHome() {
        return clbHome;
    }

    public void setClbHome(String clbHome) {
        this.clbHome = clbHome;
    }

    public String getClbGuess() {
        return clbGuess;
    }

    public void setClbGuess(String clbGuess) {
        this.clbGuess = clbGuess;
    }

    public String getPost_create_time() {
        return post_create_time;
    }

    public void setPost_create_time(String post_create_time) {
        this.post_create_time = post_create_time;
    }

    public int getTiSoHome() {
        return tiSoHome;
    }

    public void setTiSoHome(int tiSoHome) {
        this.tiSoHome = tiSoHome;
    }

    public int getTiSoGuess() {
        return tiSoGuess;
    }

    public void setTiSoGuess(int tiSoGuess) {
        this.tiSoGuess = tiSoGuess;
    }
}
