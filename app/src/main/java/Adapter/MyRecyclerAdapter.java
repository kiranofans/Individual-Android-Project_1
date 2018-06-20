package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yamibo.bbs.splashscreen.R;

import java.util.*;

import Model.ForumsListItem;
import Model.PostsListItems;
import Base_View_Holder.BaseViewHolder;
import Model.Base_Items_Model;
import Model.Constants;

/**Created by 300231329 on 5/29/2018.*/
/**Using Dynamic Method Dispatch or Runtime Polymorphism*/
public class MyRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    private Context context;
    private List<?extends Base_Items_Model> anyTypeItems;
    private OnItemClickListener listener;

    //An interface way to create OnItemClick events
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //Define the constructor for both context and listItem
    public MyRecyclerAdapter(Context context, List<? extends Base_Items_Model> listItems) {
        this.context = context;
        anyTypeItems = listItems;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case Constants.ViewTypes.FORUMS_TYPES:
                v = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.list_items_forums, parent, false);
                return new ForumsHolder(v,viewType);
            case Constants.ViewTypes.POSTS_TYPES:
                v = LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.list_items_posts, parent, false);
                return new PostsHolder(v, viewType);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
       holder.bind(anyTypeItems.get(position));
    }
    @Override
    public int getItemCount() {
        return anyTypeItems.size();
    }
    @Override
    public int getItemViewType(int position){
        //Get the view type
        return anyTypeItems.get(position).getViewType();
    }

    /*ForumsHolder*/
    public class ForumsHolder extends BaseViewHolder<ForumsListItem> {
        private TextView titleTv, descriptTv,
                numOfDailyNewPosts;
        private ImageView imgIcons;

        public ForumsHolder(View itemView, int viewType) {
            super(itemView);
            //things to display in RecyclerView
            descriptTv = (TextView) itemView.findViewById(R.id.descriptionTxt);
            titleTv = (TextView) itemView.findViewById(R.id.forumsTxt);
            numOfDailyNewPosts = (TextView) itemView.findViewById(R.id.numOfDailyNewPosts);
            imgIcons = (ImageView) itemView.findViewById(R.id.forumsTitleImg);

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
        public void bind(ForumsListItem obj) {
            String forumsImgUrls=obj.getImgUrl();
            //Set TextView texts here
            descriptTv.setText(obj.getDescription());
            titleTv.setText(obj.getForumsTitles());
            numOfDailyNewPosts.setText(obj.getNumOftodayPosts());
           Picasso.with(context).load(forumsImgUrls).fit().centerInside()
                    .into(imgIcons);
        }
    }

    /*PostsHolder*/
    public class PostsHolder extends BaseViewHolder<PostsListItems>{
        private TextView titleTv, lastposterTv, numOfViewersTv,authors,lastReplyDate;
        private TextView numOfReplies,postDates;
        private ImageView imgIcons;
        private PostsListItems postObj;

        public PostsHolder(View itemView, int viewType) {
            super(itemView);
            //Declare Views
            lastposterTv = (TextView) itemView.findViewById(R.id.lastRepliedPosters);
            titleTv = (TextView) itemView.findViewById(R.id.postTitle);
            numOfViewersTv = (TextView) itemView.findViewById(R.id.numOfViews);
            authors=(TextView)itemView.findViewById(R.id.author);
            imgIcons = (ImageView) itemView.findViewById(R.id.postImgs);
            numOfReplies=(TextView)itemView.findViewById(R.id.replyTxt);

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
        MyRecyclerAdapter adp;
        @Override
        public void bind(PostsListItems obj) {
            String avatarUrls=obj.getAvatarUrls();
            lastposterTv.setText(obj.getLastReplies());
            titleTv.setText(obj.getPostTitles());
            numOfViewersTv.setText(obj.getViewers());
            authors.setText(obj.getAuthors());
            numOfReplies.setText(obj.getReplies());

        }

    }
}

