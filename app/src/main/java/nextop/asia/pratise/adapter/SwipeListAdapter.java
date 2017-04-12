package nextop.asia.pratise.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nextop.asia.pratise.model.Movie;
import pratice.asia.nextop.tabsmaterial.R;

/**
 * Created by billymobile on 11/18/15.
 */
public class SwipeListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Movie> mMovieList;
    private String[] mBgColors;
    @Override
    public int getCount() {
        return mMovieList.size();
    }

    public SwipeListAdapter(Context context, List<Movie> movieList) {
        this.mContext = context;
        this.mMovieList = movieList;
        mBgColors = context.getResources().getStringArray(R.array.array_bg_movie);
    }

    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(mInflater == null){
            mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.movie_list_item,null);
        }

        TextView lblSerial = (TextView) convertView.findViewById(R.id.lblSerial);
        TextView lblTitle = (TextView) convertView.findViewById(R.id.lblTitle);

        lblSerial.setText(String.valueOf(mMovieList.get(position).id));
        lblTitle.setText(mMovieList.get(position).title);

        String color = mBgColors[position % mBgColors.length];
        lblSerial.setBackgroundColor(Color.parseColor(color));

        return convertView;
    }
}
