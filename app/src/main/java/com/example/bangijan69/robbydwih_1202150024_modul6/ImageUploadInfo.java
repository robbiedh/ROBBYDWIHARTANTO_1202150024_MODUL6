package com.example.bangijan69.robbydwih_1202150024_modul6;

/**
 * Created by bangijan69 on 3/31/2018.
 */

public class ImageUploadInfo {
    public String imageName;

    public String imageURL;

    public ImageUploadInfo() {

    }

    public ImageUploadInfo(String name, String url) {

        this.imageName = name;
        this.imageURL= url;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

}
