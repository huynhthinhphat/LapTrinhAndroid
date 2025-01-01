package com.example.gplxhanga.entities;

import android.os.Parcelable;

import java.io.Serializable;

public class BienBaoGiaoThong implements Serializable {
    private Long id;
    private String name;
    private String content;
    private String image;
    private String signType;

    public BienBaoGiaoThong(String name, String content, String image) {
        this.name = name;
        this.content = content;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
