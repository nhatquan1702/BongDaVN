package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuKienTrongTran {
    @SerializedName("player_id")
    @Expose
    private String idCauThu;
    @SerializedName("clb_name")
    @Expose
    private String tenCLB;

    public String getIdCauThu() {
        return idCauThu;
    }

    public void setIdCauThu(String idCauThu) {
        this.idCauThu = idCauThu;
    }

    public String getTenCLB() {
        return tenCLB;
    }

    public void setTenCLB(String tenCLB) {
        this.tenCLB = tenCLB;
    }

    public String getTenCauThu() {
        return tenCauThu;
    }

    public void setTenCauThu(String tenCauThu) {
        this.tenCauThu = tenCauThu;
    }

    public int getSoAo() {
        return soAo;
    }

    public void setSoAo(int soAo) {
        this.soAo = soAo;
    }

    public int getPhutThu() {
        return phutThu;
    }

    public void setPhutThu(int phutThu) {
        this.phutThu = phutThu;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @SerializedName("player_international_name")
    @Expose
    private String tenCauThu;
    @SerializedName("number")
    @Expose
    private int soAo;
    @SerializedName("start_time")
    @Expose
    private int phutThu;
    @SerializedName("event_name")
    @Expose
    private String noiDung;

    public SuKienTrongTran(String idCauThu, String tenCLB, String tenCauThu, int soAo, int phutThu, String noiDung) {
        this.idCauThu = idCauThu;
        this.tenCLB = tenCLB;
        this.tenCauThu = tenCauThu;
        this.soAo = soAo;
        this.phutThu = phutThu;
        this.noiDung = noiDung;
    }
}
