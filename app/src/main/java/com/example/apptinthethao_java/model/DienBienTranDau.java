package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DienBienTranDau {
    @SerializedName("clb_home_name")
    @Expose
    private String doiNha;
    @SerializedName("clb_guess_name")
    @Expose
    private String  doiKhach;
    @SerializedName("match_result")
    @Expose
    private String ketQua;
    @SerializedName("match_happen_time")
    @Expose
    private String thoiGian;

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getDoiNha() {
        return doiNha;
    }

    public void setDoiNha(String doiNha) {
        this.doiNha = doiNha;
    }

    public String getDoiKhach() {
        return doiKhach;
    }

    public void setDoiKhach(String doiKhach) {
        this.doiKhach = doiKhach;
    }

    public String getKetQua() {
        return ketQua;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }




    public DienBienTranDau(String thoiGian, String doiNha, String doiKhach, String ketQua) {
        this.thoiGian = thoiGian;
        this.doiNha = doiNha;
        this.doiKhach = doiKhach;
        this.ketQua = ketQua;
    }
}
