package com.uc.mvvm.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.uc.mvvm.util.Constants;

public class TvShow implements Parcelable {

    @SerializedName("id")
    private String id_show;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("backdrop_path")
    private String cover;

    @SerializedName("name")
    private String title;

    @SerializedName("overview")
    private String description;

    @SerializedName("first_air_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private String vote_average;

    public TvShow(){
    }

    public TvShow(String id_show, String popularity, String poster, String cover, String title, String description, String releaseDate, String vote_average) {
        this.id_show = id_show;
        this.popularity = popularity;
        this.poster = poster;
        this.cover = cover;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.vote_average = vote_average;
    }

    public String getId_show() {
        return id_show;
    }

    public void setId_show(String id_show) {
        this.id_show = id_show;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPoster() {
        return Constants.BASE_IMAGE_URL+poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getCover() {
        return Constants.BASE_IMAGE_URL+cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    protected TvShow(Parcel in) {
        this.id_show = in.readString();
        this.popularity = in.readString();
        this.poster = in.readString();
        this.cover = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.releaseDate = in.readString();
        this.vote_average = in.readString();
    }

    public static Creator<TvShow> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_show);
        dest.writeString(this.popularity);
        dest.writeString(this.poster);
        dest.writeString(this.cover);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.releaseDate);
        dest.writeString(this.vote_average);
    }
}
