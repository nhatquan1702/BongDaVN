package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CauThu_DoiHinh {
    @SerializedName("player_id")
    @Expose
    private String idCauThu;

    @SerializedName("player_international_name")
    @Expose
    private String tenCauThu;

    @SerializedName("player_img_url")
    @Expose
    private String imgCauThu;

    @SerializedName("position")
    @Expose
    private String viTri;

    @SerializedName("number")
    @Expose
    private int soAo;

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

    public CauThu_DoiHinh(String idCauThu, String tenCauThu,String imgCauThu, String viTri, int soAo) {
        this.idCauThu = idCauThu;
        this.tenCauThu = tenCauThu;
        this.imgCauThu = imgCauThu;
        this.viTri = viTri;
        this.soAo = soAo;
    }
}
