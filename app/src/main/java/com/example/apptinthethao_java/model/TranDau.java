package com.example.apptinthethao_java.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class TranDau implements Parcelable {
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

    protected TranDau(Parcel in) {
        idTranDau = in.readString();
        thoiGian = in.readInt();
        diaDiem = in.readString();
        ketQua = in.readString();
        moTa = in.readString();
    }

    public static final Creator<TranDau> CREATOR = new Creator<TranDau>() {
        @Override
        public TranDau createFromParcel(Parcel in) {
            return new TranDau(in);
        }

        @Override
        public TranDau[] newArray(int size) {
            return new TranDau[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(thoiDiem);
        dest.writeValue(doiNha);
        dest.writeValue(doiKhach);
        dest.writeInt(thoiGian);
        dest.writeString(ketQua);

    }
}
