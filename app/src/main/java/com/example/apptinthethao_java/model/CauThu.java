package com.example.apptinthethao_java.model;

public class CauThu {
    private String idCauThu;
    private String tenCauThu;
    private int chieuCao;
    private int canNang;
    private int tuoi;
    private int giaTriCauThu;
    private String phongDo;
    private String quocTich;

    public CauThu(String idCauThu, String tenCauThu, int chieuCao, int canNang, int tuoi, int giaTriCauThu, String phongDo, String quocTich) {
        this.idCauThu = idCauThu;
        this.tenCauThu = tenCauThu;
        this.chieuCao = chieuCao;
        this.canNang = canNang;
        this.tuoi = tuoi;
        this.giaTriCauThu = giaTriCauThu;
        this.phongDo = phongDo;
        this.quocTich = quocTich;
    }

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

    public int getChieuCao() {
        return chieuCao;
    }

    public void setChieuCao(int chieuCao) {
        this.chieuCao = chieuCao;
    }

    public int getCanNang() {
        return canNang;
    }

    public void setCanNang(int canNang) {
        this.canNang = canNang;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public int getGiaTriCauThu() {
        return giaTriCauThu;
    }

    public void setGiaTriCauThu(int giaTriCauThu) {
        this.giaTriCauThu = giaTriCauThu;
    }

    public String getPhongDo() {
        return phongDo;
    }

    public void setPhongDo(String phongDo) {
        this.phongDo = phongDo;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }
}
