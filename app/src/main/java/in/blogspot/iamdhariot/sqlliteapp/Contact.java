package in.blogspot.iamdhariot.sqlliteapp;

/**
 * Created by Dhariot on 07-Feb-18.
 */

public class Contact {
    int mId;
    String mName;
    String mMobileNo;

    public Contact() {
    }

    public Contact(int mId, String mName, String mMobileNo) {
        this.mId = mId;
        this.mName = mName;
        this.mMobileNo = mMobileNo;
    }

    public Contact(String mName, String mMobileNo) {
        this.mName = mName;
        this.mMobileNo = mMobileNo;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmMobileNo() {
        return mMobileNo;
    }

    public void setmMobileNo(String mMobileNo) {
        this.mMobileNo = mMobileNo;
    }
}
