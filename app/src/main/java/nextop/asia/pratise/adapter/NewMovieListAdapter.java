package nextop.asia.pratise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import nextop.asia.pratise.model.NewMovie;
import pratice.asia.nextop.tabsmaterial.MyApplication;
import pratice.asia.nextop.tabsmaterial.R;

/**
 * Created by billymobile on 11/19/15.
 */
public class NewMovieListAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater mInflater;
    private List<NewMovie> movieItems;
    ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();

    public NewMovieListAdapter(Context context, List<NewMovie> movieItems) {
        this.context = context;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(mInflater == null){
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.new_movie_item,null);
            viewHolder = new ViewHolder();
            viewHolder.thumbNail = (NetworkImageView)convertView.findViewById(R.id.thumbnail);
            viewHolder.lblTitle = (TextView)convertView.findViewById(R.id.lblNewMovieTitle);
            viewHolder.lblRating = (TextView) convertView.findViewById(R.id.lblNewMovieRating);
            viewHolder.lblGenre = (TextView) convertView.findViewById(R.id.lblGenre);
            viewHolder.lblYear = (TextView) convertView.findViewById(R.id.lblReleaseYear);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        NewMovie movie = movieItems.get(position);
        if(movie !=null){
            viewHolder.thumbNail.setImageUrl(movie.getThumbnailUrl(),imageLoader);
            viewHolder.lblTitle.setText(movie.getTitle());
            viewHolder.lblRating.setText("Rating:" + movie.getRating());

            String genreStr = "";
            for (String str : movie.getGenre()) {
                genreStr += str + ", ";
            }
            genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                    genreStr.length() - 2) : genreStr;

            viewHolder.lblGenre.setText(genreStr);
            viewHolder.lblYear.setText(movie.getYear()+"");
        }
        return convertView;
    }

     static class ViewHolder{
        protected NetworkImageView thumbNail;
        protected TextView lblTitle;
        protected TextView lblRating;
        protected TextView lblGenre;
        protected TextView lblYear;
    }
}
