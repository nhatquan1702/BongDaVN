package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TranDau {

    private String idTranDau;
    private Date thoiDiem;
    private String thoiGian;
    private String diaDiem;
    private String doiNha;
    private String logoDoiNha;
    private String doiKhach;
    private String logoDoiKhach;
    private String ketQua;
    private String moTa;
    public  TranDau(){

    }

    public TranDau(String idTranDau, Date thoiDiem, String thoiGian, String diaDiem, String doiNha,
                   String logoDoiNha, String doiKhach, String logoDoiKhach, String ketQua, String moTa) {
        this.idTranDau = idTranDau;
        this.thoiDiem = thoiDiem;
        this.thoiGian = thoiGian;
        this.diaDiem = diaDiem;
        this.doiNha = doiNha;
        this.logoDoiNha = logoDoiNha;
        this.doiKhach = doiKhach;
        this.logoDoiKhach = logoDoiKhach;
        this.ketQua = ketQua;
        this.moTa = moTa;
    }

    public String getIdTranDau() {
        return idTranDau;
    }

    public void setIdTranDau(String idTranDau) {
        this.idTranDau = idTranDau;
    }

    public Date getThoiDiem() {
        return thoiDiem;
    }

    public void setThoiDiem(Date thoiDiem) {
        this.thoiDiem = thoiDiem;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian){
        this.thoiGian = thoiGian;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getLogoDoiNha() {
        return logoDoiNha;
    }

    public void setLogoDoiNha(String logoDoiNha) {
        this.logoDoiNha = logoDoiNha;
    }

    public String getLogoDoiKhach() {
        return logoDoiKhach;
    }

    public void setLogoDoiKhach(String logoDoiKhach) {
        this.logoDoiKhach = logoDoiKhach;
    }
}
