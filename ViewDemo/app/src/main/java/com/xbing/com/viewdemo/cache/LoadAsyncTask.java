package com.xbing.com.viewdemo.cache;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MQ on 2017/6/27.
 */

public class LoadAsyncTask extends AsyncTask<Object, String, Boolean> {
    private static final String TAG = "ImageFetcher";
    private static final int IO_BUFFER_SIZE = 8 * 1024;//8kb
    private ILoadStatus listener;

    public interface ILoadStatus {
        void OnBeforeLoad();

        void OnDataTransfer();

        void OnDataLoaded();
    }

    public LoadAsyncTask(ILoadStatus listener) {
        this.listener = listener;
    }


    @Override
    protected void onPreExecute() {
        if (listener != null) {
            listener.OnBeforeLoad();
        }
    }

    @Override
    protected Boolean doInBackground(Object... params) {
        try {
            String url = (String) params[1];
            String key = Util.hashKeyForDisk(url);
            DiskLruCache diskLruCache = (DiskLruCache) params[0];

            DiskLruCache.Editor editor = diskLruCache.edit(key);
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                if (downloadUrlToStream(url, outputStream)) {
                    publishProgress("");
                    Log.e("DiskLruCache","写入缓存，key:" + key);
                    editor.commit();
                } else {
                    editor.abort();
                }
            }
            diskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    protected void onProgressUpdate(String... values) {
        if (listener != null) {
            listener.OnDataTransfer();
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        Log.e("TTT", "aBoolean is " + aBoolean);
        if (listener != null) {
            listener.OnDataLoaded();
        }
    }

    /**
     * Download a bitmap from a URL and write the content to an output stream.
     *
     * @param urlString The URL to fetch
     * @return true if successful, false otherwise
     */
    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;

        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error in downloadBitmap - " + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
            }
        }
        return false;
    }
}
