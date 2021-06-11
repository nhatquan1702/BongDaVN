package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranDauSapDienRa {
    public String getIdTran() {
        return idTran;
    }

    public void setIdTran(String idTran) {
        this.idTran = idTran;
    }

    @SerializedName("match_id")
    @Expose
    private String idTran;
    public TranDauSapDienRa(String idTran, String doiNha, String doiKhach, String ketQua, String thoiGian, String avtDoiNha, String avtDoiKhach) {
        this.idTran = idTran;
        this.doiNha = doiNha;
        this.doiKhach = doiKhach;
        this.ketQua = ketQua;
        this.thoiGian = thoiGian;
        this.avtDoiNha = avtDoiNha;
        this.avtDoiKhach = avtDoiKhach;
    }
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

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getAvtDoiNha() {
        return avtDoiNha;
    }

    public void setAvtDoiNha(String avtDoiNha) {
        this.avtDoiNha = avtDoiNha;
    }

    public String getAvtDoiKhach() {
        return avtDoiKhach;
    }

    public void setAvtDoiKhach(String avtDoiKhach) {
        this.avtDoiKhach = avtDoiKhach;
    }

    @SerializedName("clb_home_img_url")
    @Expose
    private String avtDoiNha;
    @SerializedName("clb_guess_img_url")
    @Expose
    private String  avtDoiKhach;

}
