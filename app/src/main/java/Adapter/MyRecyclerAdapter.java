package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
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
import Model.Hits;
import Model.Image;
import Model.PostListItems;
import Adapter.Base_View_Holder.BaseViewHolder;
import Model.Base_Items_Model;
import Utility.Constants;

/**Using Dynamic Method Dispatch or Runtime Polymorphism*/
public class MyRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    private Context context;
    private List<?extends Base_Items_Model> anyTypeItems;
    private OnItemClickListener listener;
    private List<? extends Base_Items_Model> items;
    private SwipeRefreshLayout swiper;
    private boolean flag=false;
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

    public void clear(){
        anyTypeItems.clear();
        notifyDataSetChanged();
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
            case Constants.ViewTypes.GALLERY:
                v=LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.gallery_cardview,parent,false);
                return new GalleryHolder(v,viewType);
            case Constants.ViewTypes.HITS:
                v=LayoutInflater.from(parent.getContext()).inflate(R.layout.hits_card_view,
                        parent,false);
                return new HitsHolder(v,viewType);
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
    public class ForumsHolder extends BaseViewHolder<ForumsListItem>{
        private TextView titleTv, descriptTv, numOfDailyNewPosts;
        private ImageView imgIcons;

        public ForumsHolder(View itemView, int viewType) {
            super(itemView);
            //things to display in RecyclerView
          // sectionTitle=(TextView)itemView.findViewById(R.id.catListNames1);
            descriptTv = (TextView) itemView.findViewById(R.id.descriptionTxt);
            titleTv = (TextView) itemView.findViewById(R.id.forumsTitleTV);
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
           /*Picasso.with(context).load(forumsImgUrls).fit().centerInside()
                    .into(imgIcons);*/
        }
    }

    /**PostsHolder*/
    public class PostsHolder extends BaseViewHolder<PostListItems>{
        private TextView titleTv, lastposterTv, postDate,authors,lastReplyDate;

        private ImageView imgIcons;
        public PostsHolder(View itemView, int viewType) {
            super(itemView);
            //Declare Views
            lastposterTv = (TextView) itemView.findViewById(R.id.lastRepliedPosters);
            titleTv = (TextView) itemView.findViewById(R.id.postTitle);
            postDate = (TextView) itemView.findViewById(R.id.hitsDateTV);
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
        public void bind(PostListItems obj) {
            String avatarUrls=obj.getAvatarUrls();
            lastposterTv.setText(obj.getLastReplies());
            titleTv.setText(obj.getPostTitles());
            postDate.setText(obj.getPost_dates());
            authors.setText(obj.getAuthors());
        }
    }
    /**PostsHolder*/
    public class GalleryHolder extends BaseViewHolder<Image>{
        View v;
        private TextView titleTv, lastposterTv, postDate,authors,lastReplyDate;
        private TextView numOfReplies,postDates;
        private ImageView galleryImgs;

        public GalleryHolder(View itemView,int viewType) {
            super(itemView);
            galleryImgs=(ImageView)itemView.findViewById(R.id.galleryImgs);
        }

        @Override
        public void bind(Image obj) {
            //Load Image urls
            Picasso.with(context).load(obj.getImgUrls()).fit().centerInside()
                    .error(R.drawable.ic_menu_camera)
                    .into(galleryImgs);

        }
    }
    private class HitsHolder extends BaseViewHolder<Hits>{
        View v;
        private TextView hitsTitle,hitsPostDates;
        public HitsHolder(View itemView,int viewType) {
            super(itemView);
            hitsPostDates=(TextView)itemView.findViewById(R.id.hitsDateTV);
            hitsTitle=(TextView)itemView.findViewById(R.id.hitTitleTV);
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
        public void bind(Hits obj) {
            //For top hits recView
            hitsTitle.setText(obj.getHitsTitle());
            hitsPostDates.setText(obj.getHitsPostDate());
        }
    }
}

