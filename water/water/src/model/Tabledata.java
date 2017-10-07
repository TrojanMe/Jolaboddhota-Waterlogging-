package model;

/**
 * Created by User on 09-May-17.
 */
public class Tabledata {
    String dataid;
    String userid;
    String locx;
    String locy;
    String customdata;
    String custommode;
    String approximatedata;
    String time;
    String comment;

    public Tabledata() {

    }

    public Tabledata(String dataid, String userid, String locx, String locy, String customdata, String custommode, String approximatedata, String time, String comment) {
        this.dataid = dataid;
        this.userid = userid;
        this.locx = locx;
        this.locy = locy;
        this.customdata = customdata;
        this.custommode = custommode;
        this.approximatedata = approximatedata;
        this.time = time;
        this.comment = comment;
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLocx() {
        return locx;
    }

    public void setLocx(String locx) {
        this.locx = locx;
    }

    public String getLocy() {
        return locy;
    }

    public void setLocy(String locy) {
        this.locy = locy;
    }

    public String getCustomdata() {
        return customdata;
    }

    public void setCustomdata(String customdata) {
        this.customdata = customdata;
    }

    public String getCustommode() {
        return custommode;
    }

    public void setCustommode(String custommode) {
        this.custommode = custommode;
    }

    public String getApproximatedata() {
        return approximatedata;
    }

    public void setApproximatedata(String approximatedata) {
        this.approximatedata = approximatedata;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
