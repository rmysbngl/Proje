package com.example.developer.proje;

/**
 * Created by rumeysal on 8/1/17.
 */

public class Kisiler  {
    String name;
    String kullaniciadi;
    String email;
    String sifre;
    String doğum;
    String sehir;
    String cinsiyet;

    public Kisiler(String name,String kullaniciadi, String email, String sifre, String doğum, String sehir, String cinsiyet) {
        this.name = name;
        this.email = email;
        this.kullaniciadi=kullaniciadi;
        this.sifre = sifre;
        this.doğum = doğum;
        this.sehir = sehir;
        this.cinsiyet = cinsiyet;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSifre() {
        return sifre;
    }

    public String getDoğum() {
        return doğum;
    }

    public String getSehir() {
        return sehir;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }
}
