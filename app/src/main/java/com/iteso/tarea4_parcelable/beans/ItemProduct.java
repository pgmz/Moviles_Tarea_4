package com.iteso.tarea4_parcelable.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemProduct implements Parcelable{

    public static final Parcelable.Creator<ItemProduct> CREATOR = new Parcelable.Creator<ItemProduct>(){

        @Override
        public ItemProduct createFromParcel(Parcel parcel) {
            return new ItemProduct(parcel);
        }

        @Override
        public ItemProduct[] newArray(int i) {
            return new ItemProduct[i];
        }
    };

    private static int itemProductCounter = 0;

    private int code = 0;
    private int image;
    private String title;
    private String store;
    private String location;
    private String phone;
    private String description;

    public ItemProduct(){
        code = itemProductCounter;
        itemProductCounter++;
        setImage(0);
        setTitle("");
        setStore("");
        setLocation("");
        setPhone("");
        setDescription("");
    }

    public ItemProduct(Parcel in){
        setCode(in.readInt());
        setImage(in.readInt());
        setTitle(in.readString());
        setStore(in.readString());
        setLocation(in.readString());
        setPhone(in.readString());
        setDescription(in.readString());
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode(){return code;}

    public void setCode(int code) {this.code = code;}

    @Override
    public String toString() {
        return "ItemProduct{" +
                "image=" + image +
                ", title='" + title + '\'' +
                ", store='" + store + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.getCode());
        parcel.writeInt(this.getImage());
        parcel.writeString(this.getTitle());
        parcel.writeString(this.getStore());
        parcel.writeString(this.getLocation());
        parcel.writeString(this.getPhone());
        parcel.writeString(this.getDescription());
    }

}
