package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KetQua_TranDau {
    @SerializedName("match_id")
    @Expose
    private String matchID ;

    @SerializedName("clb_home_name")
    @Expose
    private String clbHome;

    @SerializedName("clb_guess_name")
    @Expose
    private String clbGuess;

    @SerializedName("h")
    @Expose
    private int tiSoHome;

    @SerializedName("g")
    @Expose
    private int tiSoGuess;

    public KetQua_TranDau() {
        this.matchID = "";
        this.clbHome = "";
        this.clbGuess = "";
        this.tiSoHome = 0;
        this.tiSoGuess = 0;
    }

    public KetQua_TranDau(String matchID, String clbHome, String clbGuess, int tiSoHome, int tiSoGuess) {
        this.matchID = matchID;
        this.clbHome = clbHome;
        this.clbGuess = clbGuess;
        this.tiSoHome = tiSoHome;
        this.tiSoGuess = tiSoGuess;
    }

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
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
