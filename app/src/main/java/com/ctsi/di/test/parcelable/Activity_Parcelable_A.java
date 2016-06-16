package com.ctsi.di.test.parcelable;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.ArrayMap;
import android.util.Log;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by doulala on 16/5/6.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class Activity_Parcelable_A extends Activity {

    User user0, user1, user2;
    ArrayList<User> users = new ArrayList<>();
    ArrayMap<String,Account> map=new ArrayMap<>();

    {
        user0 = new User();
        user0.setName("liyao");
        user0.setAge(18);
//        user0.setAccount(new Account("123"));
        HashMap<String,User> module=new HashMap<>();


        user1 = new User();
        user1.setName("liyao1");
        user1.setAge(19);
//        user1.setAccount(new Account("1231"));
        module.put("1",user1);
        user0.setModules(module);


        user2 = new User();
        user2.setName("liyao2");
        user2.setAge(19);
//        user2.setAccount(new Account("1232"));


        users.add(user0);
        users.add(user1);
        users.add(user2);
//
//        map.put("1",new Account("1"));
//        map.put("2",new Account("2"));
//        map.put("3",new Account("3"));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Intent intent = new Intent(Activity_Parcelable_A.this, Activity_Parcelable_B.class);
//        intent.putExtra("parcelable", Parcels.wrap(user0));
//
//        startActivity(intent);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor1=preferences.edit();

        editor1.putString("a","a");
        editor1.commit();
        SharedPreferences.Editor editor2=preferences.edit();
        editor2.putString("a","b");
        editor2.commit();
        editor1.putString("b","b");
        editor1.commit();
        String a=preferences.getString("a","");
        Log.e("a",a);



    }
}
