package nextop.asia.pratise.model;

/**
 * Created by billymobile on 11/19/15.
 */
public class NewFeed {
    private int id;
    private String lblName, lblStatus, lblImage, lblProfilePic, lblTimeStamp, lblUrl;

    public NewFeed() {
    }

    public NewFeed(int id, String lblName, String lblStatus, String lblImage, String lblProfilePic, String lblTimeStamp, String lblUrl) {
        this.id = id;
        this.lblName = lblName;
        this.lblStatus = lblStatus;
        this.lblImage = lblImage;
        this.lblProfilePic = lblProfilePic;
        this.lblTimeStamp = lblTimeStamp;
        this.lblUrl = lblUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLblName() {
        return lblName;
    }

    public void setLblName(String lblName) {
        this.lblName = lblName;
    }

    public String getLblStatus() {
        return lblStatus;
    }

    public void setLblStatus(String lblStatus) {
        this.lblStatus = lblStatus;
    }

    public String getLblImage() {
        return lblImage;
    }

    public void setLblImage(String lblImage) {
        this.lblImage = lblImage;
    }

    public String getLblProfilePic() {
        return lblProfilePic;
    }

    public void setLblProfilePic(String lblProfilePic) {
        this.lblProfilePic = lblProfilePic;
    }

    public String getLblTimeStamp() {
        return lblTimeStamp;
    }

    public void setLblTimeStamp(String lblTimeStamp) {
        this.lblTimeStamp = lblTimeStamp;
    }

    public String getLblUrl() {
        return lblUrl;
    }

    public void setLblUrl(String lblUrl) {
        this.lblUrl = lblUrl;
    }
}
