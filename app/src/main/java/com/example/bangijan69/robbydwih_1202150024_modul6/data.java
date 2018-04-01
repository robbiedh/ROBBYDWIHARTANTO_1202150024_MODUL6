package com.example.bangijan69.robbydwih_1202150024_modul6;

import java.io.Serializable;

/**
 * Created by bangijan69 on 3/30/2018.
 */

public class data implements Serializable {
    String judul;
    String post;

    data(String judul, String post){
        this.judul=judul;
        this.post=post;

    }
    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getJudul() {
        return judul;
    }

    public String getPost() {
        return post;
    }

}
