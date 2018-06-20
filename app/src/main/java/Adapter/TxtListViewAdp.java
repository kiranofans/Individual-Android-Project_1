package Adapter;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yamibo.bbs.splashscreen.R;

import java.util.ArrayList;
import java.util.List;

public class TxtListViewAdp extends ListFragment implements View.OnClickListener{
    private Context context;
    private static List<String> marqueeList;
    public View OnCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.list_marquee, container,false);
        String[] abc={"A","B"};
        marqueeList=new ArrayList<>();

        for(int i=0;i<abc.length;i++){
            marqueeList.add(abc[i]);
        }

        return v;
    }
    public void onListItemClick(ListView list, View v, int position, long id){
        ArrayAdapter arrAdp=ArrayAdapter.createFromResource
                (context, R.array.yamibo_api_urls,R.layout.list_marquee);
        setListAdapter(arrAdp);
        getListView().setOnItemClickListener((AdapterView.OnItemClickListener) this);

    }
    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

    }
    @Override
    public void onClick(View v) {
      int position=0;
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
}
