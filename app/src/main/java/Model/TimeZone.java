package Model;

public class TimeZone {
    String mDSTOffset;
    String mRAWOffset;
    String mStatus;
    String mTimeZoneId;
    String mTimeZoneName;

    public String getDSTOffset() {
        return mDSTOffset;
    }

    public String getRAWOffset() {
        return mRAWOffset;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getTimeZoneId() {
        return mTimeZoneId;
    }

    public String getTimeZoneName() {
        return mTimeZoneName;
    }
}
