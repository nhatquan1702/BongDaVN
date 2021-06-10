package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CauThuDetail {
    @SerializedName("player_id")
    @Expose
    private String idCauThu;

    @SerializedName("player_international_name")
    @Expose
    private String tenCauThu;

    @SerializedName("player_img_url")
    @Expose
    private String imgCauThu;

    @SerializedName("p_position")
    @Expose
    private String viTri;

    @SerializedName("p_number")
    @Expose
    private int soAo;

    @SerializedName("player_birthday")
    @Expose
    private String dob;

    @SerializedName("player_height")
    @Expose
    private String player_height;

    @SerializedName("player_foot")
    @Expose
    private String player_foot;

    @SerializedName("player_nationality")
    @Expose
    private String nationality;

    @SerializedName("clb_name")
    @Expose
    private String clb_name;

    @SerializedName("main")
    @Expose
    private int main;

    @SerializedName("sub")
    @Expose
    private int sub;

    @SerializedName("p_out")
    @Expose
    private int out;

    @SerializedName("p_in")
    @Expose
    private int in;

    @SerializedName("assist")
    @Expose
    private int assist;

    @SerializedName("yellow")
    @Expose
    private int yellow;

    @SerializedName("red")
    @Expose
    private int red;

    @SerializedName("p_value")
    @Expose
    private int value;

    @SerializedName("goal")
    @Expose
    private int goal;


    public String getIdCauThu() {
        return idCauThu;
    }

    public void setIdCauThu(String idCauThu) {
        this.idCauThu = idCauThu;
    }

    public String getTenCauThu() {
        return tenCauThu;
    }

    public void setTenCauThu(String tenCauThu) {
        this.tenCauThu = tenCauThu;
    }

    public String getImgCauThu() {
        return imgCauThu;
    }

    public void setImgCauThu(String imgCauThu) {
        this.imgCauThu = imgCauThu;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public int getSoAo() {
        return soAo;
    }

    public void setSoAo(int soAo) {
        this.soAo = soAo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPlayer_height() {
        return player_height;
    }

    public void setPlayer_height(String player_height) {
        this.player_height = player_height;
    }

    public String getPlayer_foot() {
        return player_foot;
    }

    public void setPlayer_foot(String player_foot) {
        this.player_foot = player_foot;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getClb_name() {
        return clb_name;
    }

    public void setClb_name(String clb_name) {
        this.clb_name = clb_name;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public CauThuDetail(String idCauThu, String tenCauThu, String imgCauThu, String viTri, int soAo, String dob, String player_height, String player_foot, String nationality, String clb_name, int main, int sub, int out, int in, int assist, int yellow, int red, int value, int goal) {
        this.idCauThu = idCauThu;
        this.tenCauThu = tenCauThu;
        this.imgCauThu = imgCauThu;
        this.viTri = viTri;
        this.soAo = soAo;
        this.dob = dob;
        this.player_height = player_height;
        this.player_foot = player_foot;
        this.nationality = nationality;
        this.clb_name = clb_name;
        this.main = main;
        this.sub = sub;
        this.out = out;
        this.in = in;
        this.assist = assist;
        this.yellow = yellow;
        this.red = red;
        this.value = value;
        this.goal = goal;
    }
}
