package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yamibo.bbs.splashscreen.R;

import java.util.*;

import Model.ForumsListItem;
import Model.PostsListItems;
import Base_View_Holder.BaseViewHolder;
import Model.Base_Items_Model;
import Model.Constants;

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
                return new PostsHolder(v,viewType);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        holder.bind(anyTypeItems.get(position));
    }
    @Override/**Get the view types*/
    public int getItemViewType(int position){
        return anyTypeItems.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return anyTypeItems.size();
    }

    /**ForumsHolder*/
    public class ForumsHolder extends BaseViewHolder<ForumsListItem> {
        private TextView titleTv, descriptTv, numOfDailyNewPosts;
        private ImageView imgIcons;

        public ForumsHolder(View itemView, int viewType) {
            super(itemView);
            //things to display in RecyclerView
          // sectionTitle=(TextView)itemView.findViewById(R.id.catListNames1);
            descriptTv = (TextView) itemView.findViewById(R.id.descriptionTxt);
            titleTv = (TextView) itemView.findViewById(R.id.forumsTxt);
            numOfDailyNewPosts = (TextView) itemView.findViewById(R.id.numOfDailyNewPosts);
            imgIcons = (ImageView) itemView.findViewById(R.id.forumsTitleImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SectionRecycleViewAdapter sectionAdp=null;
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
           /*Picasso.with(context).load(forumsImgUrls).fit().centerInside()
                    .into(imgIcons);*/
        }
    }

    /**PostsHolder*/
    public class PostsHolder extends BaseViewHolder<PostsListItems>{
        private TextView titleTv, lastposterTv, postDate,authors,lastReplyDate;
        private TextView numOfReplies,postDates;
        private ImageView imgIcons;

        public PostsHolder(View itemView, int viewType) {
            super(itemView);
            //Declare Views
            lastposterTv = (TextView) itemView.findViewById(R.id.lastRepliedPosters);
            titleTv = (TextView) itemView.findViewById(R.id.postTitle);
            postDate = (TextView) itemView.findViewById(R.id.postDateTV);
            authors=(TextView)itemView.findViewById(R.id.author);
            imgIcons = (ImageView) itemView.findViewById(R.id.postImgs);

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
        public void bind(PostsListItems obj) {
            String avatarUrls=obj.getAvatarUrls();

            lastposterTv.setText(obj.getLastReplies());
            titleTv.setText(obj.getPostTitles());
            postDate.setText(obj.getPost_dates());
            authors.setText(obj.getAuthors());

        }

    }
    /**PostsHolder*/
    public class SectionViewHolder extends BaseViewHolder<SectionRecycleViewAdapter.Sections> {
        private TextView titleTv;

        public SectionViewHolder(View itemView, int viewType) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.catListNames);
        }

        @Override
        public void bind(SectionRecycleViewAdapter.Sections obj) {

        }
    }
}

