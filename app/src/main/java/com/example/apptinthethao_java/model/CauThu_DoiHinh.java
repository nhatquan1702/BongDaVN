package com.example.apptinthethao_java.model;

import java.util.Date;

public class CauThu_DoiHinh {
    private String idCauThu;

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

    private String tenCauThu;
    private String viTri;
    private int soAo;

    public CauThu_DoiHinh(String idCauThu, String tenCauThu, String viTri, int soAo) {
        this.idCauThu = idCauThu;
        this.tenCauThu = tenCauThu;
        this.viTri = viTri;
        this.soAo = soAo;
    }


}
