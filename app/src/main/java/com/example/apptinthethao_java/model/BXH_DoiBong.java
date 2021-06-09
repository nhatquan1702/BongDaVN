package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BXH_DoiBong {
    @SerializedName("club_name")
    @Expose
    private String TenDoiBong = "";

    @SerializedName("match")
    @Expose
    private int sotran;

    @SerializedName("win")
    @Expose
    private int thang;

    @SerializedName("banthang")
    @Expose
    private int tongBanThang;

    @SerializedName("draw")
    @Expose
    private int hoa;

    @SerializedName("lose")
    @Expose
    private int thua;

    @SerializedName("banthua")
    @Expose
    private int tongBanThua;

    @SerializedName("hieuso")
    @Expose
    private int hieuso;

    @SerializedName("diem")
    @Expose
    private int diem;

    public BXH_DoiBong(String tenDoiBong) {
        this.TenDoiBong = tenDoiBong;
        this.sotran = 0;
        this.thang = 0;
        this.tongBanThang = 0;
        this.hoa = 0;
        this.thua = 0;
        this.tongBanThua = 0;
        this.hieuso = 0;
        this.diem = 0;
    }

    public BXH_DoiBong(String tenDoiBong, int sotran, int thang, int tongBanThang, int hoa, int thua, int tongBanThua) {
        this.TenDoiBong = tenDoiBong;
        this.sotran = sotran;
        this.thang = thang;
        this.tongBanThang = tongBanThang;
        this.hoa = hoa;
        this.thua = thua;
        this.tongBanThua = tongBanThua;
        this.hieuso = this.tongBanThang-this.tongBanThua;
        this.diem = this.thang*3 + this.hoa*1;
    }

    public String getTenDoiBong() {
        return TenDoiBong;
    }

    public void setTenDoiBong(String tenDoiBong) {
        TenDoiBong = tenDoiBong;
    }

    public int getSotran() {
        return sotran;
    }

    public void setSotran(int sotran) {
        this.sotran += sotran;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
        this.diem=(this.thang*3)+this.hoa;
    }

    public int getTongBanThang() {
        return tongBanThang;
    }

    public void setTongBanThang(int tongBanThang) {
        this.tongBanThang = tongBanThang;
    }

    public int getHoa() {
        return hoa;
    }

    public void setHoa(int hoa) {
        this.hoa = hoa;
        this.diem=(this.thang*3)+this.hoa;
    }

    public int getThua() {
        return thua;
    }

    public void setThua(int thua) {
        this.thua = thua;
    }

    public int getTongBanThua() {
        return tongBanThua;
    }

    public void setTongBanThua(int tongBanThua) {
        this.tongBanThua += tongBanThua;
    }

    public int getHieuso() {
        return hieuso;
    }

    public void setHieuso(int hieuso) {
        this.hieuso = hieuso;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
}
