package com.yamibo.bbs.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yamibo.bbs.splashscreen.Fragments.AdminFragment;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;

import java.util.List;

import com.yamibo.bbs.data.Model.ForumsListItemMod;

public class ForumsRecView1Adapter extends RecyclerView.Adapter<ForumsRecView1Adapter.SingleViewHolder> implements View.OnClickListener{
    private Context context;

    private List<ForumsListItemMod> itemsList;
    private OnItemClickListener listener;

    @Override
    public void onClick(View v) {

    }


    //An interface way to create OnItemClick events
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ForumsRecView1Adapter(Context _context, List<ForumsListItemMod> forumsList){
        this.context=_context;
        itemsList=forumsList;
    }
    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate
                (R.layout.list_items_forums,null);
        return new SingleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder holder, int position) {
        ForumsListItemMod forumsList=itemsList.get(position);

        //Calling the txtViews
        holder.descriptTv.setText(forumsList.getDescription());
        holder.numOfDailyNewPosts.setText(forumsList.getNumOftodayPosts());
        holder.titleTv.setText(forumsList.getForumsTitles());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
    public class SingleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTv, descriptTv, numOfDailyNewPosts;
        private ImageView imgIcons;

        public SingleViewHolder(View itemView) {
            super(itemView);
            descriptTv = (TextView) itemView.findViewById(R.id.descriptionTxt);
            titleTv = (TextView) itemView.findViewById(R.id.forumsTitleTV);
            numOfDailyNewPosts = (TextView) itemView.findViewById(R.id.numOfDailyNewPosts);
            imgIcons = (ImageView) itemView.findViewById(R.id.forumsTitleImg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (pos!=RecyclerView.NO_POSITION) {
                //Launch fragment from RecView inside another Fragment
                MainNavTabActivity launchFrag=(MainNavTabActivity) v.getContext();
                launchFrag.getSupportFragmentManager()
                        .beginTransaction().replace(R.id.rootViewPage,
                        new AdminFragment()).addToBackStack(null).commit();
            }
        }
    }
}
