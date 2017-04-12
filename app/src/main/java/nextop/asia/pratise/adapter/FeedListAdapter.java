package nextop.asia.pratise.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import nextop.asia.pratise.customview.FeedImageView;
import nextop.asia.pratise.model.NewFeed;
import pratice.asia.nextop.tabsmaterial.MyApplication;
import pratice.asia.nextop.tabsmaterial.R;

/**
 * Created by billymobile on 11/19/15.
 */
public class FeedListAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater mInflater;
    private List<NewFeed> mNewFeeds;
    ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();

    public FeedListAdapter(Context context, List<NewFeed> mNewFeeds) {
        this.context = context;
        this.mNewFeeds = mNewFeeds;
    }

    @Override
    public int getCount() {
        return mNewFeeds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(mInflater == null){
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (imageLoader == null)
            imageLoader = MyApplication.getInstance().getImageLoader();

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.feed_row_item,null);
            viewHolder = new ViewHolder();
            viewHolder.feedImageView = (FeedImageView)convertView.findViewById(R.id.feedImageView);
            viewHolder.networkImageView = (NetworkImageView)convertView.findViewById(R.id.profilePic);
            viewHolder.lblName = (TextView) convertView.findViewById(R.id.lblName);
            viewHolder.lblTimestamp = (TextView) convertView.findViewById(R.id.lblTimestamp);
            viewHolder.lblStatus = (TextView) convertView.findViewById(R.id.lblStatusMsg);
            viewHolder.lblUrl = (TextView) convertView.findViewById(R.id.lblUrl);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NewFeed item = mNewFeeds.get(position);

        viewHolder.lblName.setText(item.getLblName());
        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getLblTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        viewHolder.lblTimestamp.setText(timeAgo);

        // Chcek for empty status message
        if (!TextUtils.isEmpty(item.getLblStatus())) {
            viewHolder.lblStatus.setText(item.getLblStatus());
            viewHolder.lblStatus.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            viewHolder.lblStatus.setVisibility(View.GONE);
        }

        // Checking for null feed url
        if (item.getLblUrl() != null) {
            viewHolder.lblUrl.setText(Html.fromHtml("<a href=\"" + item.getLblUrl() + "\">"
                    + item.getLblUrl() + "</a> "));

            // Making url clickable
            viewHolder.lblUrl.setMovementMethod(LinkMovementMethod.getInstance());
            viewHolder.lblUrl.setVisibility(View.VISIBLE);
        } else {
            // url is null, remove from the view
            viewHolder.lblUrl.setVisibility(View.GONE);
        }

        // user profile pic
        viewHolder.networkImageView.setImageUrl(item.getLblProfilePic(), imageLoader);

        // Feed image
        if (item.getLblImage() != null) {
            viewHolder.feedImageView.setImageUrl(item.getLblImage(), imageLoader);
            viewHolder.feedImageView.setVisibility(View.VISIBLE);
            viewHolder.feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            viewHolder.feedImageView.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ViewHolder{
        protected FeedImageView feedImageView;
        protected NetworkImageView networkImageView;
        protected TextView lblName;
        protected TextView lblTimestamp;
        protected TextView lblStatus;
        protected TextView lblUrl;
    }
}
