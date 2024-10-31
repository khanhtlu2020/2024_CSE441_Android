package com.example.drivingtestapp;

public class RoadSign {
    private String tenBienBao;
    private String noidungBienBao;
    private String hinh;

    public RoadSign(String tenBienBao, String noidungBienBao, String hinh) {
        this.tenBienBao = tenBienBao;
        this.noidungBienBao = noidungBienBao;
        this.hinh = hinh;
    }

    public String getTenBienBao() {
        return tenBienBao;
    }

    public void setTenBienBao(String tenBienBao) {
        this.tenBienBao = tenBienBao;
    }

    public String getNoidungBienBao() {
        return noidungBienBao;
    }

    public void setNoidungBienBao(String noidungBienBao) {
        this.noidungBienBao = noidungBienBao;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

}



