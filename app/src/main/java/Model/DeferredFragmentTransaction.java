package Model;

import android.support.v4.app.Fragment;

public abstract class DeferredFragmentTransaction {
    private int contentFrameId;
    private Fragment replacingFrag;

    public abstract void commit();

    public int getContentFrameId() {
        return contentFrameId;
    }

    public void setContentFrameId(int contentFrameId) {
        this.contentFrameId = contentFrameId;
    }

    public Fragment getReplacingFrag() {
        return replacingFrag;
    }

    public void setReplaceFrag(Fragment replacingFrag) {
        this.replacingFrag = replacingFrag;
    }

}
