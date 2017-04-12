package nextop.asia.pratise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nextop.asia.pratise.model.NewSong;
import pratice.asia.nextop.tabsmaterial.MyApplication;
import pratice.asia.nextop.tabsmaterial.R;

/**
 * Created by billymobile on 11/19/15.
 */
public class NewSongAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater mInflater;
    private List<NewSong> mNewSongList;
    ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();

    public NewSongAdapter(Context context, List<NewSong> newSongList) {
        this.context = context;
        this.mNewSongList = newSongList;
    }

    @Override
    public int getCount() {
        return mNewSongList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if(mInflater == null){
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(imageLoader == null){
            imageLoader = MyApplication.getInstance().getImageLoader();
        }
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.music_row_item,null);
            viewHolder= new ViewHolder();
            viewHolder.song_icon = (NetworkImageView)convertView.findViewById(R.id.song_icon);
            viewHolder.lblTitle = (TextView) convertView.findViewById(R.id.lblTitle);
            viewHolder.lblArtist = (TextView) convertView.findViewById(R.id.lblArtist);
            viewHolder.lblDuration = (TextView) convertView.findViewById(R.id.lblDuration);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        NewSong newSong = mNewSongList.get(position);
        viewHolder.song_icon.setImageUrl(newSong.getmImageUrl(),imageLoader);
        viewHolder.lblTitle.setText(newSong.getmTitle());
        viewHolder.lblArtist.setText(newSong.getmArtist());
        viewHolder.lblDuration.setText(newSong.getmDuration());
        return convertView;
    }

    static class ViewHolder{
        protected NetworkImageView song_icon;
        protected TextView lblTitle;
        protected TextView lblArtist;
        protected TextView lblDuration;
    }
}
