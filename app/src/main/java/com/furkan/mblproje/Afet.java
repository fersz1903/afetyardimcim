package com.furkan.mblproje;

class Afet {
    private String ad;
    private String aciklama;
    private String detay;
    private int resimId;

    public Afet(String ad, String aciklama,String detay, int resimId) {
        this.ad = ad;
        this.aciklama = aciklama;
        this.resimId = resimId;
        this.detay=detay;
    }

    public String getAd() {
        return ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    public int getResimId() {
        return resimId;
    }
    public String detay() {
        return detay;
    }
}
