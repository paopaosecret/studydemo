package com.xbing.com.viewdemo.service.cpsp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by bing.zhao on 2017/3/28.
 */

public class CPSharedPreferences implements SharedPreferences {

    private static final String TAG = "SettingSharePref";
    private Context mContext;
    private String mFileName;
    private static final Uri mUrl = SPContentProvider.CONTENT_URI;

    public CPSharedPreferences(Context context, String fileName)
    {
        mContext = context;
        mFileName = fileName;
    }

    @Override
    public Map<String, ?> getAll() {
        return null;
    }

    @Nullable
    @Override
    public String getString(String key, @Nullable String defValue) {
        Cursor c = mContext.getContentResolver().query(mUrl, new String[]{"value"}, "(fileName = ? and key= ?)", new String[]{mFileName, key}, null);
        try
        {
            if (c != null && c.moveToFirst())
            {
                defValue = c.getString(0);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (c != null) c.close();
        }
        return defValue;
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        Cursor c = mContext.getContentResolver().query(mUrl, new String[]{"value"}, "(fileName = ? and key= ?)", new String[]{mFileName, key}, null);
        try
        {
            if (c != null && c.moveToFirst())
            {
                defValues = getStringSetFromJson(c.getString(0));
            }
        } catch (Exception e)
        {
            Log.e(TAG, e.toString(), e);
        } finally
        {
            if (c != null) c.close();
        }
        return defValues;
    }

    @Override
    public int getInt(String key, int defValue) {
        Cursor c = mContext.getContentResolver().query(mUrl, new String[]{"value"}, "(fileName = ? and key= ?)", new String[]{mFileName, key}, null);
        try
        {
            if (c != null && c.moveToFirst())
            {
                defValue = c.getInt(0);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (c != null) c.close();
        }
        return defValue;
    }

    @Override
    public long getLong(String key, long defValue) {
        Cursor c = mContext.getContentResolver().query(mUrl, new String[]{"value"}, "(fileName = ? and key= ?)", new String[]{mFileName, key}, null);
        try
        {
            if (c != null && c.moveToFirst())
            {
                defValue = c.getLong(0);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (c != null) c.close();
        }
        return defValue;
    }

    @Override
    public float getFloat(String key, float defValue) {
        Cursor c = mContext.getContentResolver().query(mUrl, new String[]{"value"}, "(fileName = ? and key= ?)", new String[]{mFileName, key}, null);
        try
        {
            if (c != null && c.moveToFirst())
            {
                defValue = c.getFloat(0);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (c != null) c.close();
        }
        return defValue;
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        Cursor c = mContext.getContentResolver().query(mUrl, new String[]{"value"}, "(fileName = ? and key= ?)", new String[]{mFileName, key}, null);
        try
        {
            if (c != null && c.moveToFirst())
            {
                String string = c.getString(0);
                if ("true".equalsIgnoreCase(string) || "false".equalsIgnoreCase(string))
                {
                    defValue = Boolean.parseBoolean(string);// c.getInt(0)==1;
                } else
                {
                    defValue = c.getInt(0) == 1;
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (c != null) c.close();
        }
        return defValue;
    }

    @Override
    public boolean contains(String key) {
        boolean ret = false;
        Cursor c = mContext.getContentResolver().query(mUrl, new String[]{"value"}, "(fileName = ? and key= ?)", new String[]{mFileName, key}, null);
        try
        {
            if (c != null && c.moveToFirst())
            {
                ret = true;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (c != null) c.close();
        }
        return ret;
    }

    @Override
    public Editor edit() {
        return new EditorImpl();
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

    }

    public static Set<String> getStringSetFromJson(String values)
    {
        return new Gson().fromJson(values, new TypeToken<HashSet<String>>()
        {
        }.getType());
    }

    public static String toJson(Set<String> values)
    {
        return new Gson().toJson(values);
    }

    private class EditorImpl implements Editor
    {
        private boolean mClear = false;
        private ContentValues mContentValues = new ContentValues();

        @Override
        public Editor putString(String key, String value)
        {
            mContentValues.put(key, value);

            return this;
        }


        @Override
        public Editor putStringSet(String key, Set<String> values)
        {
            synchronized (this)
            {
                HashSet set = (values == null) ? null : new HashSet<String>(values);
                mContentValues.put(key, toJson(set));
                return this;
            }
        }

        @Override
        public Editor putInt(String key, int value)
        {
            mContentValues.put(key, value);
            return this;
        }

        @Override
        public Editor putLong(String key, long value)
        {
            mContentValues.put(key, value);
            return this;
        }

        @Override
        public Editor putFloat(String key, float value)
        {
            mContentValues.put(key, value);
            return this;
        }

        @Override
        public Editor putBoolean(String key, boolean value)
        {
            mContentValues.put(key, value);
            return this;
        }

        @Override
        public Editor remove(String key)
        {
            mContentValues.remove(key);
            String[] selectionArgs = new String[]{mFileName, key};
            String selection = "(fileName = ? and key= ?)";
            mContext.getContentResolver().delete(mUrl, selection, selectionArgs);
            return this;
        }

        @Override
        public Editor clear()
        {
            mClear = true;
            mContentValues.clear();
            String[] selectionArgs = new String[]{mFileName};
            String selection = "(fileName = ?)";
            mContext.getContentResolver().delete(mUrl, selection, selectionArgs);
            return this;
        }

        @Override
        public boolean commit()
        {
            if (mContentValues != null && mContentValues.size() > 0)
            {
                String selection = "(fileName = ?)";
                String[] selectionArgs = new String[]{mFileName};
                mContext.getContentResolver().update(mUrl, mContentValues, selection, selectionArgs);
                mContentValues.clear();
            }
            return true;
        }

        @Override
        public void apply()
        {
            commit();
        }
    }
}
