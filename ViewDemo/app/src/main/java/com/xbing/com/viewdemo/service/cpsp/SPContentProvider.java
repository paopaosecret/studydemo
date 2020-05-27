package com.xbing.com.viewdemo.service.cpsp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Created by bing.zhao on 2017/3/28.
 */

public class SPContentProvider extends ContentProvider {

    public static String TAG = SPContentProvider.class.getSimpleName();

    public static String AUTHORITY = "com.xbing.com.viewdemo.service.cpsp.SPContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY+"/data");
    public static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    public static final int DATA = 1;
    public static  String PATH = "data";
    private static final Object sLock = new Object();

    static{
        URI_MATCHER.addURI(AUTHORITY, PATH, DATA);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        synchronized (sLock)
        {
            Object result = null;
            MatrixCursor ret = null;
            switch (URI_MATCHER.match(uri))
            {
                case DATA:
                    ret = initColumnsCursor(projection);
                    String fileName = selectionArgs[0];
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS);
                    result = sharedPreferences.getAll().get(selectionArgs[1]);
                    if (result instanceof Set)
                    {
                        result = new Gson().toJson(result);
                    }
                    if (result != null)
                    {
                        Object[] row = new Object[]{
                                result};
                        ret.addRow(row);
                    }
            }
            Log.v(TAG, "query " + result + " of  " + Arrays.toString(selectionArgs));
            return ret;
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match)
        {
            case DATA:
                return "vnd.android.cursor.dir/data";
            default:
                throw new IllegalArgumentException("Unknown URL");
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (URI_MATCHER.match(uri))
        {
            case DATA:
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        synchronized (sLock)
        {
            switch (URI_MATCHER.match(uri))
            {
                case DATA:
                    Log.v(TAG, "delete " + Arrays.toString(selectionArgs));
                    String fileName = selectionArgs[0];

                    String key = null;
                    if (selection.contains("key") && selectionArgs.length >= 2)
                    {
                        key = selectionArgs[1];
                    }
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (key != null)
                    {
                        editor.remove(key).apply();
                    } else
                    {
                        editor.clear().apply();
                    }
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        boolean change = false;
        synchronized (sLock) {
            switch (URI_MATCHER.match(uri)) {
                case DATA:

                    Log.v(TAG, "update " + Arrays.toString(selectionArgs) + " " + values.size());
                    String fileName = selectionArgs[0];
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (values != null && values.size() > 0) {
                        Set<Map.Entry<String, Object>> entries = values.valueSet();
                        for (Map.Entry<String, Object> entry : entries) {
                            Object obj = entry.getValue();
                            Log.v(TAG, "update " + entry.getKey() + " : " + obj);
                            if (obj instanceof String) {
                                editor.putString(entry.getKey(), (String) obj);
                            } else if (obj instanceof Integer) {
                                editor.putInt(entry.getKey(), (Integer) obj);
                            } else if (obj instanceof Long) {
                                editor.putLong(entry.getKey(), (Long) obj);
                            } else if (obj instanceof Float) {
                                editor.putFloat(entry.getKey(), (Float) obj);
                            } else if (obj instanceof Boolean) {
                                editor.putBoolean(entry.getKey(), ((Boolean) obj));
                            } else {
                                Log.v(TAG, "update unknown instanceof ");
                            }
                        }
                        editor.commit();
                        change = true;
                    }


            }

            if (change) getContext().getContentResolver().notifyChange(uri, null);
            return 0;
        }
    }


    private MatrixCursor initColumnsCursor(String[] columns)
    {
        MatrixCursor ret = new MatrixCursor(columns);
        ret.moveToFirst();
        return ret;
    }
}
