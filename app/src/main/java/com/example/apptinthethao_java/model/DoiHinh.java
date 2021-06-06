package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DoiHinh {
    @SerializedName("home_main")
    @Expose
    private ArrayList<CauThu_DoiHinh> arrayListHomeMain;

    public ArrayList<CauThu_DoiHinh> getArrayListHomeMain() {
        return arrayListHomeMain;
    }

    public void setArrayListHomeMain(ArrayList<CauThu_DoiHinh> arrayListHomeMain) {
        this.arrayListHomeMain = arrayListHomeMain;
    }

    public ArrayList<CauThu_DoiHinh> getArrayListHomeSub() {
        return arrayListHomeSub;
    }

    public void setArrayListHomeSub(ArrayList<CauThu_DoiHinh> arrayListHomeSub) {
        this.arrayListHomeSub = arrayListHomeSub;
    }

    public ArrayList<CauThu_DoiHinh> getArrayListGuessMain() {
        return arrayListGuessMain;
    }

    public void setArrayListGuessMain(ArrayList<CauThu_DoiHinh> arrayListGuessMain) {
        this.arrayListGuessMain = arrayListGuessMain;
    }

    public ArrayList<CauThu_DoiHinh> getArrayListGuessSub() {
        return arrayListGuessSub;
    }

    public void setArrayListGuessSub(ArrayList<CauThu_DoiHinh> arrayListGuessSub) {
        this.arrayListGuessSub = arrayListGuessSub;
    }

    @SerializedName("home_sub")
    @Expose
    private ArrayList<CauThu_DoiHinh> arrayListHomeSub;
    @SerializedName("guess_main")
    @Expose
    private ArrayList<CauThu_DoiHinh> arrayListGuessMain;
    @SerializedName("guess_sub")
    @Expose
    private ArrayList<CauThu_DoiHinh> arrayListGuessSub;


    public DoiHinh(ArrayList<CauThu_DoiHinh> arrayListHomeMain, ArrayList<CauThu_DoiHinh> arrayListHomeSub, ArrayList<CauThu_DoiHinh> arrayListGuessMain, ArrayList<CauThu_DoiHinh> arrayListGuessSub) {
        this.arrayListHomeMain = arrayListHomeMain;
        this.arrayListHomeSub = arrayListHomeSub;
        this.arrayListGuessMain = arrayListGuessMain;
        this.arrayListGuessSub = arrayListGuessSub;
    }
}
