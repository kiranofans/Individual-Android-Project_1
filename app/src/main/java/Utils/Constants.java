package Utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static Utils.Constants.ViewTypes.FORUMS_TYPES;
import static Utils.Constants.ViewTypes.GALLERY;
import static Utils.Constants.ViewTypes.HITS;
import static Utils.Constants.ViewTypes.MANGAS;
import static Utils.Constants.ViewTypes.POSTS_TYPES;
import static Utils.Constants.ViewTypes.USERS;

public class Constants {
    /**Class to define each base ViewType for RecyclerView*/
    @IntDef ({FORUMS_TYPES,POSTS_TYPES,USERS,MANGAS,GALLERY,HITS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewTypes{
        int FORUMS_TYPES=100;
        int POSTS_TYPES=200;
        int USERS=300;
        int MANGAS=400;
        int GALLERY=500;
        int HITS=600;
    }
}
