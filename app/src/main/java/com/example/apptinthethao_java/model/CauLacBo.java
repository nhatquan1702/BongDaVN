package com.example.apptinthethao_java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CauLacBo {
    @SerializedName("clb_name")
    @Expose
    private String tenCLB;
    @SerializedName("clb_img_url")
    @Expose
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTenCLB() {
        return tenCLB;
    }

    public void setTenCLB(String tenCLB) {
        this.tenCLB = tenCLB;
    }

    public CauLacBo(String link, String tenCLB) {
        this.link = link;
        this.tenCLB = tenCLB;
    }

    public CauLacBo() {
    }
}
