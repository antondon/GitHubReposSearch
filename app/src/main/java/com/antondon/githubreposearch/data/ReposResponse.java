package com.antondon.githubreposearch.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class ReposResponse implements Parcelable {

    public final static Parcelable.Creator<ReposResponse> CREATOR = new Creator<ReposResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReposResponse createFromParcel(Parcel in) {
            return new ReposResponse(in);
        }

        public ReposResponse[] newArray(int size) {
            return (new ReposResponse[size]);
        }

    };
    @SerializedName("total_count")
    @Expose
    public Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    public Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    public List<Repository> repositories = null;

    protected ReposResponse(Parcel in) {
        this.totalCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.incompleteResults = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.repositories, (com.antondon.githubreposearch.data.Repository.class.getClassLoader()));
    }

    public ReposResponse() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totalCount);
        dest.writeValue(incompleteResults);
        dest.writeList(repositories);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(repositories).append(totalCount).append(incompleteResults).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ReposResponse) == false) {
            return false;
        }
        ReposResponse rhs = ((ReposResponse) other);
        return new EqualsBuilder().append(repositories, rhs.repositories).append(totalCount, rhs.totalCount).append(incompleteResults, rhs.incompleteResults).isEquals();
    }

}
