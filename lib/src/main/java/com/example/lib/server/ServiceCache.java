package com.example.lib.server;

import android.os.IBinder;

import com.example.lib.helper.collection.ArrayMap;

import java.util.Map;


public class ServiceCache {
    private static final Map<String, IBinder> sCache = new ArrayMap<>(5);

    public static void addService(String name, IBinder service) {
        sCache.put(name, service);
    }

    public static IBinder removeService(String name) {
        return sCache.remove(name);
    }

    public static IBinder getService(String name) {
        return sCache.get(name);
    }

}
