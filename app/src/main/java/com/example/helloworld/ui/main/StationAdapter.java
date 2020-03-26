package com.example.helloworld.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.helloworld.Profile;
import com.example.helloworld.R;
import com.example.helloworld.StationItem;
import com.squareup.picasso.Picasso;
import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Object> recyclerViewItems;
    private final Context mContext;
    Profile fragment_profilefragment;

    public StationAdapter(Context context, List<Object> recyclerViewItems, Profile fragment_profilefragment){
        this.mContext = context;
        this.recyclerViewItems = recyclerViewItems;
        this.fragment_profilefragment = fragment_profilefragment;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_station, null);
        return new MenuItemViewHolder(itemLayoutView);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position){
        MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;
        final StationItem fp = (StationItem) recyclerViewItems.get(position);
        menuItemHolder.title.setText(fp.getTitle());
        menuItemHolder.synopsis.setText(fp.getSynopsis());
        String url = fp.getUrlimages();
        Picasso.get().load(url).into(menuItemHolder.thumb);
    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView thumb;
        public TextView synopsis;
        public LinearLayout lineLayout;

        MenuItemViewHolder(View itemLayoutView){
            super(itemLayoutView);
            title = (TextView) itemLayoutView.findViewById(R.id.title);
            thumb = (ImageView) itemLayoutView.findViewById(R.id.imagethumb);
            synopsis = (TextView) itemLayoutView.findViewById(R.id.synopsis);
            lineLayout = (LinearLayout) itemLayoutView.findViewById(R.id.middlelayout);
        }

    }
}
