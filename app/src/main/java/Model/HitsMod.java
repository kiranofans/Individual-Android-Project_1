package Model;

import Utils.Constants;

public class HitsMod implements Base_Items_Model {
    private String hitsTitle,hitsPostDate;
    public HitsMod(){}

    public HitsMod(String hitsTitle, String postDate){
        this.hitsPostDate=postDate;
        this.hitsTitle=hitsTitle;
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

    public void setHitsPostDate(String hitsPostDate) {
        this.hitsPostDate = hitsPostDate;
    }

    @Override
    public int getViewType() {
        return Constants.ViewTypes.HITS;
    }
}
