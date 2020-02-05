
package com.yamibo.bbs.data.Model.UserProfileMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _1 {

    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("ratio")
    @Expose
    private String ratio;
    @SerializedName("showinthread")
    @Expose
    private Object showinthread;
    @SerializedName("allowexchangein")
    @Expose
    private Object allowexchangein;
    @SerializedName("allowexchangeout")
    @Expose
    private Object allowexchangeout;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public Object getShowinthread() {
        return showinthread;
    }

    public void setShowinthread(Object showinthread) {
        this.showinthread = showinthread;
    }

    public Object getAllowexchangein() {
        return allowexchangein;
    }

    public void setAllowexchangein(Object allowexchangein) {
        this.allowexchangein = allowexchangein;
    }

    public Object getAllowexchangeout() {
        return allowexchangeout;
    }

    public void setAllowexchangeout(Object allowexchangeout) {
        this.allowexchangeout = allowexchangeout;
    }

}
