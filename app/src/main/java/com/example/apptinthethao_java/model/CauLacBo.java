package com.example.apptinthethao_java.model;

public class CauLacBo {
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

    private String link;

    public CauLacBo(String link, String tenCLB) {
        this.link = link;
        this.tenCLB = tenCLB;
    }

    private String tenCLB;
}
