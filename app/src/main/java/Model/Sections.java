package Model;

import android.util.SparseArray;
import android.widget.TextView;
// In a fuller example, this would probably hold more data than just strings.
public class Sections {
    private TextView sectionTitleTv;

    int firstPosition;
    int sectionedPosition;
    String title;
    SparseArray<Sections> sectionList;
    public Sections(){}
    public Sections(String title, int firstPosition){
        this.firstPosition=firstPosition;
        this.title=title;
    }

    public SparseArray<Sections> getSectionList() {
        return sectionList;
    }

    public void setSectionList(SparseArray<Sections> sectionList) {
        this.sectionList = sectionList;
    }

    public TextView getSectionTitle() {
        return sectionTitleTv;
    }

    public void setSectionTitle(TextView sectionTitle) {
        this.sectionTitleTv = sectionTitle;
    }

    public int getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(int firstPosition) {
        this.firstPosition = firstPosition;
    }
    public int getSectionedPosition() {
        return sectionedPosition;
    }

    public void setSectionedPosition(int sectionedPosition) {
        this.sectionedPosition = sectionedPosition;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
