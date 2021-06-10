package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CauThu_Performance {
    @SerializedName("player_id")
    @Expose
    private String idCauThu;

    @SerializedName("start_time")
    @Expose
    private String thoiDiem;

    @SerializedName("property_value")
    @Expose
    private int phongDo;

    public String getIdCauThu() {
        return idCauThu;
    }

    public void setIdCauThu(String idCauThu) {
        this.idCauThu = idCauThu;
    }

    public String getThoiDiem() {
        return thoiDiem;
    }

    public void setThoiDiem(String thoiDiem) {
        this.thoiDiem = thoiDiem;
    }

    public int getPhongDo() {
        return phongDo;
    }

    public void setPhongDo(int phongDo) {
        this.phongDo = phongDo;
    }

    public CauThu_Performance(String idCauThu, String thoiDiem, int phongDo) {
        this.idCauThu = idCauThu;
        this.thoiDiem = thoiDiem;
        this.phongDo = phongDo;
    }
}
