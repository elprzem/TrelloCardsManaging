package przefar.trellocardsmanaging.trello_classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Przemys?aw Farion on 6/1/2015.
 */
public class List implements Parcelable {
    private String id;
    private String name;
    private Boolean closed;
    private String idBoard;
    private Double pos;
    private Board subscribed;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getClosed() {
        return closed;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public Double getPos() {
        return pos;
    }

    public Board getSubscribed() {
        return subscribed;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
