package com.antondon.githubreposearch.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Owner implements Parcelable {

    public final static Parcelable.Creator<Owner> CREATOR = new Creator<Owner>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        public Owner[] newArray(int size) {
            return (new Owner[size]);
        }

    };
    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("avatar_url")
    @Expose
    public String avatarUrl;
    @SerializedName("gravatar_id")
    @Expose
    public String gravatarId;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("html_url")
    @Expose
    public String htmlUrl;
    @SerializedName("followers_url")
    @Expose
    public String followersUrl;
    @SerializedName("following_url")
    @Expose
    public String followingUrl;
    @SerializedName("gists_url")
    @Expose
    public String gistsUrl;
    @SerializedName("starred_url")
    @Expose
    public String starredUrl;
    @SerializedName("subscriptions_url")
    @Expose
    public String subscriptionsUrl;
    @SerializedName("organizations_url")
    @Expose
    public String organizationsUrl;
    @SerializedName("repos_url")
    @Expose
    public String reposUrl;
    @SerializedName("events_url")
    @Expose
    public String eventsUrl;
    @SerializedName("received_events_url")
    @Expose
    public String receivedEventsUrl;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("site_admin")
    @Expose
    public Boolean siteAdmin;

    protected Owner(Parcel in) {
        this.login = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.avatarUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.gravatarId = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.htmlUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.followersUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.followingUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.gistsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.starredUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.subscriptionsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.organizationsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.reposUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.eventsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.receivedEventsUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.siteAdmin = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public Owner() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(login);
        dest.writeValue(id);
        dest.writeValue(avatarUrl);
        dest.writeValue(gravatarId);
        dest.writeValue(url);
        dest.writeValue(htmlUrl);
        dest.writeValue(followersUrl);
        dest.writeValue(followingUrl);
        dest.writeValue(gistsUrl);
        dest.writeValue(starredUrl);
        dest.writeValue(subscriptionsUrl);
        dest.writeValue(organizationsUrl);
        dest.writeValue(reposUrl);
        dest.writeValue(eventsUrl);
        dest.writeValue(receivedEventsUrl);
        dest.writeValue(type);
        dest.writeValue(siteAdmin);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(eventsUrl).append(siteAdmin).append(gistsUrl).append(type).append(gravatarId).append(subscriptionsUrl).append(url).append(followersUrl).append(id).append(reposUrl).append(htmlUrl).append(receivedEventsUrl).append(avatarUrl).append(followingUrl).append(organizationsUrl).append(login).append(starredUrl).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Owner) == false) {
            return false;
        }
        Owner rhs = ((Owner) other);
        return new EqualsBuilder().append(eventsUrl, rhs.eventsUrl).append(siteAdmin, rhs.siteAdmin).append(gistsUrl, rhs.gistsUrl).append(type, rhs.type).append(gravatarId, rhs.gravatarId).append(subscriptionsUrl, rhs.subscriptionsUrl).append(url, rhs.url).append(followersUrl, rhs.followersUrl).append(id, rhs.id).append(reposUrl, rhs.reposUrl).append(htmlUrl, rhs.htmlUrl).append(receivedEventsUrl, rhs.receivedEventsUrl).append(avatarUrl, rhs.avatarUrl).append(followingUrl, rhs.followingUrl).append(organizationsUrl, rhs.organizationsUrl).append(login, rhs.login).append(starredUrl, rhs.starredUrl).isEquals();
    }
}
