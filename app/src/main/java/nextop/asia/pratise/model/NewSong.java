package nextop.asia.pratise.model;

/**
 * Created by billymobile on 11/19/15.
 */
public class NewSong {
    private int mId;
    private String mTitle, mArtist, mImageUrl, mDuration ;

    public NewSong() {
    }

    public NewSong(int mId, String mTitle, String mArtist, String mImageUrl, String mDuration) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mArtist = mArtist;
        this.mImageUrl = mImageUrl;
        this.mDuration = mDuration;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmArtist() {
        return mArtist;
    }

    public void setmArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmDuration() {
        return mDuration;
    }

    public void setmDuration(String mDuration) {
        this.mDuration = mDuration;
    }
}
