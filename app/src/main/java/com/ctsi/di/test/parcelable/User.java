package com.ctsi.di.test.parcelable;




import android.os.Parcelable;
import android.util.ArrayMap;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by doulala on 16/5/6.
 */

@Parcel
public class User {

    private static final int constructID=1001;


     int age;
     String name;
     Account account;
     HashMap<String,User> modules;


    public User() {

    }

    @ParcelConstructor
    public User(int age, String name, Account account, HashMap<String, User> modules) {
        this.age = age;
        this.name = name;
        this.account = account;
        this.modules = modules;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public HashMap<String, User> getModules() {
        return modules;
    }

    public void setModules(HashMap<String, User> modules) {
        this.modules = modules;
    }

}
