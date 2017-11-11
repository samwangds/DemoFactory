package com.sam.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samwang on 2017/11/11.
 */

public class TestModel implements Parcelable {

    private String string;
    private int num;

    public TestModel(String string, int num) {
        this.string = string;
        this.num = num;
    }

    protected TestModel(Parcel in) {
        string = in.readString();
        num = in.readInt();
    }

    public static final Creator<TestModel> CREATOR = new Creator<TestModel>() {
        @Override
        public TestModel createFromParcel(Parcel in) {
            return new TestModel(in);
        }

        @Override
        public TestModel[] newArray(int size) {
            return new TestModel[size];
        }
    };

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(string);
        dest.writeInt(num);
    }
}
