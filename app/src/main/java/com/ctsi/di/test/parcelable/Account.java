package com.ctsi.di.test.parcelable;


import android.os.Parcelable;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by doulala on 16/5/6.
 */

@Parcel
public class Account {
    private static final int constructID=1000;


    String password;

    public Account(){

    }

//    public Account(String password) {
//        this.password = password;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
