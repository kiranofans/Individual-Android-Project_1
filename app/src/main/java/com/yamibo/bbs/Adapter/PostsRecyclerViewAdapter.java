package com.yamibo.bbs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.splashscreen.PostActivity;
import com.yamibo.bbs.splashscreen.R;
import com.yamibo.bbs.splashscreen.databinding.ListItemsPostsBinding;

import java.util.List;

import static Utils.AppConstants.KEY_EXTRA_FORUMS_THREAD;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder2> {
    private List<ForumThreadMod> adminThreadList;
    private Context _context;

    private View v;
    private ListItemsPostsBinding admRecViewBinding;

    public PostsRecyclerViewAdapter(Context context, List<ForumThreadMod> adminList) {
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

            openPostContent(obj);
            if(getAdapterPosition()==0){
                admRecViewBinding.threadTvSection.setVisibility(View.VISIBLE);
                admRecViewBinding.threadTvSection.setText(_context.getString(R.string.forums_threads));
            }
        }
        private void openPostContent(ForumThreadMod obj){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        Intent postContentIntent = new Intent(_context, PostActivity.class);
                        postContentIntent.putExtra(KEY_EXTRA_FORUMS_THREAD,obj);
                        _context.startActivity(postContentIntent);
                    }
                }
            });
        }
    }
}
