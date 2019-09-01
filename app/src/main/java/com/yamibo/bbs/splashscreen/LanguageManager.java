package com.yamibo.bbs.splashscreen;

import java.util.Observable;

public class LanguageManager extends Observable {
    private static LanguageManager langManager;

    private SuppportLanguage suppportLanguage;

    public static LanguageManager getInstance(){
        return langManager = new LanguageManager();
    }

    enum SuppportLanguage{
        CHINESE_SIMPLIFIED,CHINESE_TRADITIONAL,ENGLISH
    }

    public String getString(int stringResId){
        return getString(stringResId);
    }

    public void Languages(){
        switch(suppportLanguage){
            case CHINESE_SIMPLIFIED:
                //suppportLanguage.CHINESE_SIMPLIFIED;
                break;
            case CHINESE_TRADITIONAL:
                //suppportLanguage = FORUM_CHATTING_URL;
                break;
            case ENGLISH:
                //suppportLanguage = FORUM_ADMIN_URL;
                break;
        }
    }
}
