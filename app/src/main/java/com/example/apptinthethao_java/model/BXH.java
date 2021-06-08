package com.example.apptinthethao_java.model;

import java.util.ArrayList;

public class BXH {
    private String tenBXH;
    private String time;
    private ArrayList<BXH_DoiBong> BXH_DOIBONG= new ArrayList<BXH_DoiBong>();

    public BXH() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BXH(String tenBXH) {
        this.tenBXH = tenBXH;
    }

    public String getTenBXH() {
        return tenBXH;
    }

    public void setTenBXH(String tenBXH) {
        this.tenBXH = tenBXH;
    }

    public ArrayList<BXH_DoiBong> getBXH_DOIBONG() {
        return BXH_DOIBONG;
    }

    public void setBXH_DOIBONG(ArrayList<BXH_DoiBong> BXH_DOIBONG) {
        this.BXH_DOIBONG = BXH_DOIBONG;
    }

}
