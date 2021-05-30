package com.example.apptinthethao_java.model;

import java.util.Date;

public class TranDau {
    private String idTranDau;
    private Date thoiDiem;
    private int thoiGian;
    private String diaDiem;
    private CLB_DoiBong doiNha;
    private CLB_DoiBong doiKhach;
    private String ketQua;
    private String moTa;

    public TranDau(String idTranDau, Date thoiDiem, int thoiGian, String diaDiem, CLB_DoiBong doiNha, CLB_DoiBong doiKhach, String ketQua, String moTa) {
        this.idTranDau = idTranDau;
        this.thoiDiem = thoiDiem;
        this.thoiGian = thoiGian;
        this.diaDiem = diaDiem;
        this.doiNha = doiNha;
        this.doiKhach = doiKhach;
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

    public int getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(int thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public CLB_DoiBong getDoiNha() {
        return doiNha;
    }

    public void setDoiNha(CLB_DoiBong doiNha) {
        this.doiNha = doiNha;
    }

    public CLB_DoiBong getDoiKhach() {
        return doiKhach;
    }

    public void setDoiKhach(CLB_DoiBong doiKhach) {
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
}
