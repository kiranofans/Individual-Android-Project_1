package Utils;

import android.os.Parcel;
import android.os.Parcelable;

/** compiled from: Fragment */
final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR = new Creator<FragmentState>() {
        public FragmentState createFromParcel(Parcel in) {
            return new FragmentState(in);
        }

        public FragmentState[] newArray(int size) {
            return new FragmentState[size];
        }
    };

    public FragmentState(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
