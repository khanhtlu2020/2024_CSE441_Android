package com.example.drivingtestapp;

public class Learning {
    private String maCauHoi;
    private String cauHoi;
    private String dapAn;

    public Learning(String maCauHoi, String cauHoi, String dapAn) {
        this.maCauHoi = maCauHoi;
        this.cauHoi = cauHoi;
        this.dapAn = dapAn;
    }

    public String getMaCauHoi() {
        return maCauHoi;
    }

    public void setMaCauHoi(String maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

}


