package com.example.apptinthethao_java.model;

public class CLB_DoiBong {
    private String idCLB;
    private String tenCLB;
    private String thanhPho;
    private String urlLogo;

    public CLB_DoiBong(String idCLB, String tenCLB, String thanhPho, String urlLogo) {
        this.idCLB = idCLB;
        this.tenCLB = tenCLB;
        this.thanhPho = thanhPho;
        this.urlLogo = urlLogo;
    }

    public String getIdCLB() {
        return idCLB;
    }

    public void setIdCLB(String idCLB) {
        this.idCLB = idCLB;
    }

    public String getTenCLB() {
        return tenCLB;
    }

    public void setTenCLB(String tenCLB) {
        this.tenCLB = tenCLB;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
}
