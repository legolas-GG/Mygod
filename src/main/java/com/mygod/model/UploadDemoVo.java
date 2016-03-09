package com.mygod.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by legolas on 2016/1/13.
 */
public class UploadDemoVo {
    private int id;
    private MultipartFile imgFile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
}
