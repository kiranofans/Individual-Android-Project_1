package com.yamibo.bbs.data.Model;


import Utils.Constants;

public class ImageMod implements Base_Items_Model {
    private String imgName, format,size,num;
    private String uploadDate,removeDate;

    private String imgUrls;
    private int imgIds;
    public ImageMod(String imgName, String format, String size, String num) {
        this.imgName = imgName;
        this.format = format;
        this.size = size;
        this.num = num;
    }
    public ImageMod(){}//keep an empty constructor here
    public ImageMod(int imgIds){
       this.imgIds=imgIds;
    }

    public ImageMod(String urls){
        this.imgUrls=urls;
    }
    public ImageMod(String uploadDate, String removeDate){
        this.uploadDate=uploadDate;
        this.removeDate=removeDate;
    }

    //Getters and Setters
    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(String removeDate) {
        this.removeDate = removeDate;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public int getImgIds() {
        return imgIds;
    }

    public void setImgIds(int imgIds) {
        this.imgIds = imgIds;
    }

    @Override
    public int getViewType() {
        return Constants.ViewTypes.GALLERY;
    }
}