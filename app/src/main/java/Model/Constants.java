package Model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static Model.Constants.ViewTypes.FORUMS_TYPES;
import static Model.Constants.ViewTypes.MANGAS;
import static Model.Constants.ViewTypes.POSTS_TYPES;
import static Model.Constants.ViewTypes.USERS;

public class Constants {
    /**Class to define each base ViewType for RecyclerView*/
    @IntDef ({FORUMS_TYPES,POSTS_TYPES,USERS,MANGAS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewTypes{
        int FORUMS_TYPES=100;
        int POSTS_TYPES=200;
        int USERS=300;
        int MANGAS=400;
    }
}
