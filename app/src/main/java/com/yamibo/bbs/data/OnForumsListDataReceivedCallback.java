package com.yamibo.bbs.data;

import com.yamibo.bbs.data.Model.ForumListMod.ForumsListInfoMod;

import java.util.List;

public interface OnForumsListDataReceivedCallback {
    void onForumsListDataReceived(List<ForumsListInfoMod> forumsListMods);

}
