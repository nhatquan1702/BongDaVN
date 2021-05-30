package com.example.apptinthethao_java.model;

import java.util.Date;

public class CauThu_TranDau {
    private String idCauThu;
    private Date thoiDiem;
    private String viTri;
    private int soAo;

    public CauThu_TranDau(String idCauThu, Date thoiDiem, String viTri, int soAo) {
        this.idCauThu = idCauThu;
        this.thoiDiem = thoiDiem;
        this.viTri = viTri;
        this.soAo = soAo;
    }

    public String getIdCauThu() {
        return idCauThu;
    }

    public void setIdCauThu(String idCauThu) {
        this.idCauThu = idCauThu;
    }

    public Date getThoiDiem() {
        return thoiDiem;
    }

    public void setThoiDiem(Date thoiDiem) {
        this.thoiDiem = thoiDiem;
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
}
