package com.yamibo.bbs.splashscreen;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**Create this new textView to avoid affecting other widgets
* when applying Marquee*/
public class MyTextView extends AppCompatTextView {
    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public MyTextView(Context context, AttributeSet attributes){
        super(context,attributes);
    }
    public MyTextView(Context context) {
        super(context);
    }
    @Override
    public boolean isFocused(){
        return true;//make it always has focus
    }

}
