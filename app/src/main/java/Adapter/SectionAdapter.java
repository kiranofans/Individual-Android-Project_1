package Adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.yamibo.bbs.splashscreen.R;

import java.util.Arrays;
import java.util.Comparator;

import Base_View_Holder.BaseViewHolder;
/**Provided by Google*/
public class SectionAdapter extends RecyclerView.Adapter {
        private final Context mContext;
        private static final int SECTION_TYPE = 0;
        private boolean isValid = true;
        private int mSectionResourceId;
        private int mTextResourceId;
        private LayoutInflater mLayoutInflater;
        private RecyclerView.Adapter mBaseAdapter;
        private SparseArray<Sections> mSections = new SparseArray<>();

        public SectionAdapter(Context context, int sectionResourceId, int textResourceId,
                              RecyclerView.Adapter<BaseViewHolder> baseAdapter) {

            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mSectionResourceId = sectionResourceId;
            mTextResourceId = textResourceId;
            mBaseAdapter = baseAdapter;
            mContext = context;

            mBaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    isValid = mBaseAdapter.getItemCount()>0;
                    notifyDataSetChanged();
                }
                @Override
                public void onItemRangeChanged(int positionStart, int itemCount) {
                    isValid = mBaseAdapter.getItemCount()>0;
                    notifyItemRangeChanged(positionStart, itemCount);
                }

                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    isValid = mBaseAdapter.getItemCount()>0;
                    notifyItemRangeInserted(positionStart, itemCount);
                }

                @Override
                public void onItemRangeRemoved(int positionStart, int itemCount) {
                    isValid = mBaseAdapter.getItemCount()>0;
                    notifyItemRangeRemoved(positionStart, itemCount);
                }
            });
        }
        public static class SectionViewHolder extends RecyclerView.ViewHolder {

            public TextView title;

            public SectionViewHolder(View view, int mTextResourceid) {
                super(view);
                title = (TextView) view.findViewById(R.id.catListNames);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int typeView) {
            if (typeView == SECTION_TYPE) {
                View view = LayoutInflater.from(mContext).inflate
                        (mSectionResourceId, parent, false);
                return new SectionViewHolder(view,mTextResourceId);
            }else{
                return mBaseAdapter.onCreateViewHolder(parent,typeView-1);
            }
        }

    @Override
        public void onBindViewHolder(RecyclerView.ViewHolder sectionViewHolder, int position) {
            if (isSectionHeaderPosition(position)) {
                ((SectionViewHolder)sectionViewHolder).title.setText(mSections.get(position).title);
            }else{
                mBaseAdapter.onBindViewHolder(sectionViewHolder,sectionedPositionToPosition(position));
            }
        }

        @Override
        public int getItemViewType(int position) {
            return isSectionHeaderPosition(position)
                    ? SECTION_TYPE
                    : mBaseAdapter.getItemViewType(sectionedPositionToPosition(position)) +1 ;
        }


        public static class Sections {
            int firstPosition;
            int sectionedPosition;
            CharSequence title;
            public Sections(){}
            public Sections(int firstPosition, CharSequence title) {
                this.firstPosition = firstPosition;
                this.title = title;
            }

            public CharSequence getTitle() {
                return title;
            }
        }


        public void setSections(Sections[] sections) {
            mSections.clear();

            Arrays.sort(sections, new Comparator<Sections>() {
                @Override
                public int compare(Sections o, Sections o1) {
                    return (o.firstPosition == o1.firstPosition)
                            ? 0
                            : ((o.firstPosition < o1.firstPosition) ? -1 : 1);
                }
            });

            int offset = 0; // offset positions for the headers we're adding
            for (Sections section : sections) {
                section.sectionedPosition = section.firstPosition + offset;
                mSections.append(section.sectionedPosition, section);
                ++offset;
            }

            notifyDataSetChanged();
        }

        public int posToSectionedPos(int position) {
            int offset = 0;
            for (int i = 0; i < mSections.size(); i++) {
                if (mSections.valueAt(i).firstPosition > position) {
                    break;
                }
                ++offset;
            }
            return position + offset;
        }

        public int sectionedPositionToPosition(int sectionedPosition) {
            if (isSectionHeaderPosition(sectionedPosition)) {
                return RecyclerView.NO_POSITION;
            }
            int offset = 0;
            for (int i = 0; i < mSections.size(); i++) {
                if (mSections.valueAt(i).sectionedPosition > sectionedPosition) {
                    break;
                }
                --offset;
            }
            return sectionedPosition + offset;
        }

        public boolean isSectionHeaderPosition(int position) {
            return mSections.get(position) != null;
        }

    @Override
        public long getItemId(int position) {
            return isSectionHeaderPosition(position)
                    ? Integer.MAX_VALUE - mSections.indexOfKey(position)
                    : mBaseAdapter.getItemId(sectionedPositionToPosition(position));
        }

    @Override
        public int getItemCount() {
            return (isValid ? mBaseAdapter.getItemCount() + mSections.size() : 0);
        }

}
