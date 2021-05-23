package com.example.apptinthethao_java.model;

import java.util.Date;

public class CauThu_TranDau_SuKien {
    private String idSuKien;
    private CauThu idCauThu;
    private TranDau idTranDau;
    private Date thoiDiem;

    public CauThu_TranDau_SuKien(String idSuKien, CauThu idCauThu, TranDau idTranDau, Date thoiDiem) {
        this.idSuKien = idSuKien;
        this.idCauThu = idCauThu;
        this.idTranDau = idTranDau;
        this.thoiDiem = thoiDiem;
    }

    public String getIdSuKien() {
        return idSuKien;
    }

    public void setIdSuKien(String idSuKien) {
        this.idSuKien = idSuKien;
    }

    public CauThu getIdCauThu() {
        return idCauThu;
    }

    public void setIdCauThu(CauThu idCauThu) {
        this.idCauThu = idCauThu;
    }

    public TranDau getIdTranDau() {
        return idTranDau;
    }

    public void setIdTranDau(TranDau idTranDau) {
        this.idTranDau = idTranDau;
    }

    public Date getThoiDiem() {
        return thoiDiem;
    }

    public void setThoiDiem(Date thoiDiem) {
        this.thoiDiem = thoiDiem;
    }
}
