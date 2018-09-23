package com.ovdiienko.yaroslav.applicationa.dto.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ovdiienko.yaroslav.applicationa.dto.db.converter.DateTimeTypeConverter;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;


@Entity(tableName = "image")
public class ImageEntity implements Parcelable {
    public static final Creator<ImageEntity> CREATOR = new Creator<ImageEntity>() {
        @Override
        public ImageEntity createFromParcel(Parcel in) {
            return new ImageEntity(in);
        }

        @Override
        public ImageEntity[] newArray(int size) {
            return new ImageEntity[size];
        }
    };
    @PrimaryKey
    private long id;
    private int status;
    private String link;
    @SerializedName("date_time")
    @Expose
    @TypeConverters(DateTimeTypeConverter.class)
    private OffsetDateTime dateTime;

    protected ImageEntity(Parcel in) {
        id = in.readLong();
        status = in.readInt();
        link = in.readString();
        dateTime = OffsetDateTime.parse(in.readString());
    }

    public ImageEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(status);
        dest.writeString(link);
        dest.writeString(dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

}
