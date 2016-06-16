package com.ctsi.di.test.parcelable;

import android.content.Intent;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by doulala on 16/5/6.
 */
public class MapWrapper<T extends Map & Serializable> implements Serializable {

    private final T map;

    public MapWrapper(T map) {
        this.map = map;
    }

    public T getMap() {
        return map;
    }

    public static <T extends Map & Serializable> Intent
    putMapExtra(Intent intent, String name, T map) {

        return intent.putExtra(name, new MapWrapper<>(map));
    }

    public static <T extends Map & Serializable> T
    getMapExtra(Intent intent, String name)
            throws ClassCastException {

        Serializable s = intent.getSerializableExtra(name);
        return s == null ? null : ((MapWrapper<T>)s).getMap();
    }
}