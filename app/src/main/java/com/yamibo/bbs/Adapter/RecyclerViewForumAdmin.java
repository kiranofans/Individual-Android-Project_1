package com.yamibo.bbs.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.splashscreen.databinding.ListItemsPostsBinding;

import java.util.List;

public class RecyclerViewForumAdmin extends RecyclerView.Adapter<BaseViewHolder2> {
    private List<ForumThreadMod> adminThreadList;
    private Context _context;

    private View v;
    private ListItemsPostsBinding admRecViewBinding;

    public RecyclerViewForumAdmin(Context context, List<ForumThreadMod> adminList) {
        this._context = context;
        this.adminThreadList = adminList;
    }

    @NonNull
    @Override
    public BaseViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        admRecViewBinding = ListItemsPostsBinding.inflate(LayoutInflater.from(_context),
                viewGroup, false);
        return new ForumAdminHolder(admRecViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder2 baseViewHolder2, int i) {
        ForumThreadMod threadList = adminThreadList.get(i);
        baseViewHolder2.bind(adminThreadList.get(i));
    }

    @Override
    public int getItemCount() {
        return adminThreadList.size();
    }

    class ForumAdminHolder extends BaseViewHolder2<ForumThreadMod> {

        public ForumAdminHolder(ListItemsPostsBinding admBinding) {
            super(admBinding.getRoot());
            admRecViewBinding = admBinding;
        }

        @Override
        public void bind(ForumThreadMod obj) {
            admRecViewBinding.postTvAuthor.setText(obj.getAuthor());
            admRecViewBinding.postTitle.setText(obj.getSubject());
            admRecViewBinding.postTvDate.setText(obj.getDateline());
            //Glide.with(_context).load(obj.get).into(admRecViewBinding.postImgs);
        }
    }
}
