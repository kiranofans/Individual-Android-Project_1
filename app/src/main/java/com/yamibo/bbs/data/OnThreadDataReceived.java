package com.yamibo.bbs.data;

import com.yamibo.bbs.data.Model.ForumListMod.CatlistMod;
import com.yamibo.bbs.data.Model.ForumListMod.ForumsListInfoMod;
import com.yamibo.bbs.data.Model.ForumListMod.ForumsListMod;
import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;

import java.util.List;

public interface OnThreadDataReceived {
    void onThreadDataReceived(List<ForumThreadMod> threadMod);

}
