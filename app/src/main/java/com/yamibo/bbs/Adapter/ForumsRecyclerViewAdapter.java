package com.yamibo.bbs.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamibo.bbs.data.Model.ForumListMod.ForumsListInfoMod;
import com.yamibo.bbs.splashscreen.R;
import com.yamibo.bbs.splashscreen.databinding.ListItemsForumsBinding;

import java.util.List;

/**
 * Using Dynamic Method Dispatch or Runtime Polymorphism
 */
public class ForumsRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder2> {
    private Context context;

    private ListItemsForumsBinding forumsListBinding;

    private List<ForumsListInfoMod> anyTypeItems;
    private OnItemClickListener listener;

    private boolean flag = false;

    //An interface way to create OnItemClick events
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //Define the constructor for both context and listItem
    public ForumsRecyclerViewAdapter(Context context, List<ForumsListInfoMod> listItems) {
        this.context = context;
        anyTypeItems = listItems;
    }

    public void clear() {
        anyTypeItems.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        forumsListBinding = ListItemsForumsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ForumsListHolder(forumsListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder2 baseViewHolder2, int i) {
        baseViewHolder2.bind(anyTypeItems.get(i));

    }

    @Override
    public int getItemCount() {
        return anyTypeItems.size();

    }

    public class ForumsListHolder extends BaseViewHolder2<ForumsListInfoMod> {

        private int position = 0;

        public ForumsListHolder(ListItemsForumsBinding forumsBinding) {
            super(forumsListBinding.getRoot());
            forumsListBinding = forumsBinding;
        }

        @Override
        public void bind(ForumsListInfoMod obj) {
            //String forumsImgUrls=obj.get();
            //Set TextView texts here
            forumsListBinding.descriptionTxt.setText(obj.getDescription());
            forumsListBinding.forumsTitleTV.setText(obj.getName());
            forumsListBinding.numOfDailyNewPosts.setText(obj.getTodayposts());
           /* Glide.with(context).load(forumsImgUrls).centerInside().centerInside()
                    .into(imgIcons);*/
            itemOnClickListener();
            position = getAdapterPosition();

            if (position == 0) {
                forumsListBinding.forumsCatlistName.setVisibility(View.VISIBLE);
                forumsListBinding.forumsCatlistName.setText(context.getString(R.string.catlist_name_miaotang));
            } else if (position == 2) {
                forumsListBinding.forumsCatlistName.setVisibility(View.VISIBLE);
                forumsListBinding.forumsCatlistName.setText(context.getString(R.string.catlist_name_jianghu));
            } else {
                forumsListBinding.forumsCatlistName.setVisibility(View.GONE);
            }
        }

        private void itemOnClickListener() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = position;
                    if (listener != null) {
                        if (pos != RecyclerView.NO_POSITION) {
                            listener.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }


   /* private class HitsHolder extends BaseViewHolder<HitsMod>{
        View v;
        private TextView hitsTitle,hitsPostDates,hitsAuthor;
        public HitsHolder(View itemView,int viewType) {
            super(itemView);
            hitsPostDates=(TextView)itemView.findViewById(R.id.post_tv_date);
            hitsTitle=(TextView)itemView.findViewById(R.id.hitTitleTV);
            hitsAuthor = (TextView)itemView.findViewById(R.id.hits_author);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        @Override
        public void bind(HitsMod obj) {
            //For top hits recView
            hitsTitle.setText(obj.getHitsTitle());
            hitsPostDates.setText(obj.getHitsPostDate());
            hitsAuthor.setText(obj.getHitsAuthor());
        }
    }*/
}

