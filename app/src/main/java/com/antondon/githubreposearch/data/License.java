package com.antondon.githubreposearch.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class License implements Parcelable {

    public final static Parcelable.Creator<License> CREATOR = new Creator<License>() {


        @SuppressWarnings({
                "unchecked"
        })
        public License createFromParcel(Parcel in) {
            return new License(in);
        }

        public License[] newArray(int size) {
            return (new License[size]);
        }

    };
    @SerializedName("key")
    @Expose
    public String key;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("spdx_id")
    @Expose
    public String spdxId;
    @SerializedName("url")
    @Expose
    public String url;

    protected License(Parcel in) {
        this.key = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.spdxId = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
    }

    public License() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(key);
        dest.writeValue(name);
        dest.writeValue(spdxId);
        dest.writeValue(url);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(spdxId).append(name).append(url).append(key).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof License) == false) {
            return false;
        }
        License rhs = ((License) other);
        return new EqualsBuilder().append(spdxId, rhs.spdxId).append(name, rhs.name).append(url, rhs.url).append(key, rhs.key).isEquals();
    }
}
