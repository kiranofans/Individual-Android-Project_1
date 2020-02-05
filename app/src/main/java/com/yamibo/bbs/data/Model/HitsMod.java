package com.yamibo.bbs.data.Model;

import Utils.Constants;

public class HitsMod implements Base_Items_Model {
    private String hitsTitle,hitsPostDate;
    private String hitsAuthor;
    public HitsMod(){}

    public HitsMod(String hitsTitle, String postDate, String author){
        this.hitsPostDate=postDate;
        this.hitsTitle=hitsTitle;
        this.hitsAuthor = author;
    }

    public String getHitsTitle() {
        return hitsTitle;
    }

    public void setHitsTitle(String hitsTitle) {
        this.hitsTitle = hitsTitle;
    }

    public String getHitsPostDate() {
        return hitsPostDate;
    }

    public String getHitsAuthor(){
       return hitsAuthor;
    }

    public void setHitsPostDate(String hitsPostDate) {
        this.hitsPostDate = hitsPostDate;
    }

    @Override
    public int getViewType() {
        return Constants.ViewTypes.HITS;
    }
}
