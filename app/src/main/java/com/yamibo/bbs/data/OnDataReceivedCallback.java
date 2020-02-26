package com.yamibo.bbs.data;

import com.yamibo.bbs.data.Model.ForumListMod.CatlistMod;
import com.yamibo.bbs.data.Model.ForumListMod.ForumsListInfoMod;
import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;

import java.util.List;

public interface OnDataReceivedCallback {
    void onForumDataReceived(List<ForumThreadMod> threadMod);
    void onForumsListDataReceived(List<ForumsListInfoMod> forumsListInfoMods);
    void onCatlistDataReceived(List<CatlistMod> catlistMod);
}
