package przefar.trellocardsmanaging.trello_classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Przemys?aw Farion on 6/1/2015.
 */
public class Card implements Parcelable {
    private String id;
    private String dateLastActivity;
    private String desc;
    private String email;
    private String idBoard;
    private String idList;
    private String idShort;
    private String name;
    private Double pos;
    private String shortLink;
    private String shortUrl;
    private Boolean subscribed;
    private String url;

    public Card(String id, String dateLastActivity, String desc, String email, String idBoard,
                String idList, String idShort, String name, Double pos, String shortLink,
                String shortUrl, Boolean subscribed, String url) {
        this.id = id;
        this.dateLastActivity = dateLastActivity;
        this.desc = desc;
        this.email = email;
        this.idBoard = idBoard;
        this.idList = idList;
        this.idShort = idShort;
        this.name = name;
        this.pos = pos;
        this.shortLink = shortLink;
        this.shortUrl = shortUrl;
        this.subscribed = subscribed;
        this.url = url;
    }

    public Card(Parcel source) {
        String[] stringData = new String[11];
        double[] doubleData = new double[1];
        boolean[] booleanData = new boolean[1];

        source.readStringArray(stringData);
        this.id = stringData[0];
        this.dateLastActivity = stringData[1];
        this.desc = stringData[2];
        this.email = stringData[3];
        this.idBoard = stringData[4];
        this.idList = stringData[5];
        this.idShort = stringData[6];
        this.name = stringData[7];
        this.shortLink = stringData[8];
        this.shortUrl = stringData[9];
        this.url = stringData[10];

        source.readDoubleArray(doubleData);
        this.pos = doubleData[4];

        source.readBooleanArray(booleanData);
        this.subscribed = booleanData[0];
    }

    public String getId() {
        return id;
    }

    public String getDateLastActivity() {
        return dateLastActivity;
    }

    public String getDesc() {
        return desc;
    }

    public String getEmail() {
        return email;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public String getIdList() {
        return idList;
    }

    public String getIdShort() {
        return idShort;
    }

    public String getName() {
        return name;
    }

    public Double getPos() {
        return pos;
    }

    public String getShortLink() {
        return shortLink;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public String getUrl() {
        return url;
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
        dest.writeStringArray(new String[]{
                this.id, this.dateLastActivity, this.desc, this.email, this.idBoard, this.idList,
                this.idShort, this.name, this.shortLink, this.shortUrl, this.url});
        dest.writeDoubleArray(new double[]{this.pos});
        dest.writeBooleanArray(new boolean[]{this.subscribed});
    }

    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>(){

        /**
         * Create a new instance of the Parcelable class, instantiating it
         * from the given Parcel whose data had previously been written by
         * {@link Parcelable#writeToParcel Parcelable.writeToParcel()}.
         *
         * @param source The Parcel to read the object's data from.
         * @return Returns a new instance of the Parcelable class.
         */
        @Override
        public Card createFromParcel(Parcel source) {
            return new Card(source);
        }

        /**
         * Create a new array of the Parcelable class.
         *
         * @param size Size of the array.
         * @return Returns an array of the Parcelable class, with every entry
         * initialized to null.
         */
        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}
