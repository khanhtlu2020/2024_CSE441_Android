package com.example.drivingtestapp;

public class Law {
    private String tenLuat;
    private String mucPhat;

    public Law(String tenLuat, String mucPhat) {
        this.tenLuat = tenLuat;
        this.mucPhat = mucPhat;
    }

    public String getTenLuat() {
        return tenLuat;
    }

    public void setTenLuat(String tenLuat) {
        this.tenLuat = tenLuat;
    }

    public String getMucPhat() {
        return mucPhat;
    }

    public void setMucPhat(String mucPhat) {
        this.mucPhat = mucPhat;
    }

}



