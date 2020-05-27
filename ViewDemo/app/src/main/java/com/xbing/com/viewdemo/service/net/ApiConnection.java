package com.xbing.com.viewdemo.service.net;

/**
 * Created by bing.zhao on 2017/3/14.
 */


import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.xbing.com.viewdemo.service.net.https.HttpsUtils;
import com.xbing.com.viewdemo.service.net.log.LoggerInterceptor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Api Connection class used to retrieve data from the cloud.
 * Implements {@link java.util.concurrent.Callable} so when executed asynchronously can
 * return a value.
 */
public class ApiConnection
{

    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String AUTHORIZATION = "Authorization";
    private static final String ACCESS_TOKEN = "token=";
    private static String currentAccessTokenValue = "";
    private static String AUTHORIZATION_VALUE = ACCESS_TOKEN + currentAccessTokenValue;
    private static String CLIENTID="clientid";
    public static final boolean isSecuretVersion = true;

    public static String HttpErrorResponse = "";
    private static ApiConnection apiConnection;
    private String url;
    private String jsonParams;

    private ApiConnection(String url) throws MalformedURLException
    {
        this.url = url;
    }

    private ApiConnection(String url, String jsonParam) throws MalformedURLException
    {
        this.url = url;
        this.jsonParams = jsonParam;
    }

    public static ApiConnection create(String url) throws MalformedURLException
    {
        if (apiConnection == null)
        {
            apiConnection = new ApiConnection(url);
        } else
        {
            apiConnection.url = url;
        }
        return apiConnection;
    }

    public static ApiConnection create(String url, String jsonParam) throws MalformedURLException
    {
        if (apiConnection == null)
        {
            apiConnection = new ApiConnection(url, jsonParam);
        } else
        {
            apiConnection.url = url;
            apiConnection.jsonParams = jsonParam;
        }
        return apiConnection;
    }

    /**
     * Do a request to an api synchronously.
     * It should not be executed in the main thread of the application.
     *
     * @return A string response
     */
    @Nullable
    public String requestSyncGetCall()
    {
        return connectToApiByGet();
    }

    @Nullable
    public String requestSyncDeleteCall()
    {
        return connectToApiByDelete();
    }

    @Nullable
    public String requestSyncPostCall()
    {
        return connectToApiByPost();
    }

    @Nullable
    public String requestSyncPostCallUnSecuret()
    {
        return connectToApiByPostUnSecuret();
    }


    @Nullable
    public Response requestSyncDownloadGetCall()
    {
        return connectToApiByDownLoad();
    }

    @Nullable
    public String requestSyncPutCall()
    {
        return connectToApiByPut();
    }

    @Nullable
    public String requestSyncPatchCall()
    {
        return connectToApiByPatch();
    }

    private String connectToApiByGet()
    {
        final String requestUrl = url;
        OkHttpClient okHttpClient = this.createClient();
        final String authorizationValue = this.createHeadsValue();
        String response = "";
        final Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .addHeader(AUTHORIZATION, authorizationValue)
                .addHeader(CLIENTID, "1")
                .get()
                .build();

        try
        {
            response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (TextUtils.isEmpty(response))
            {
                response = HttpErrorResponse;
            }
        }
        return response;
    }

    private String connectToApiByDelete()
    {
        final String requestUrl = url;
        OkHttpClient okHttpClient = this.createClient();
        final String authorizationValue = this.createHeadsValue();
        final String params = jsonParams;
        String response = "";
        Request request = null;
        if (params != null && !params.isEmpty())
        {
            request = new Request.Builder()
                    .url(requestUrl)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                    .addHeader(AUTHORIZATION, authorizationValue)
                    .delete(RequestBody.create(MEDIA_TYPE_JSON, params))
                    .build();
        } else
        {
            request = new Request.Builder()
                    .url(requestUrl)
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                    .addHeader(AUTHORIZATION, authorizationValue)
                    .delete()
                    .build();
        }


        try
        {
            response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (TextUtils.isEmpty(response))
            {
                response = HttpErrorResponse;
            }
        }
        return response;
    }

    private String connectToApiByPost()
    {
        final String requestUrl = url;
        final String params = jsonParams;
        OkHttpClient okHttpClient = this.createClient();
        final String authorizationValue = this.createHeadsValue();
        String response = "";
        final Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .addHeader(AUTHORIZATION, authorizationValue)
                .post(RequestBody.create(MEDIA_TYPE_JSON, params))
                .build();

        try
        {
            response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (TextUtils.isEmpty(response))
            {
                response = HttpErrorResponse;
            }
        }
        return response;
    }

    private String connectToApiByPostUnSecuret()
    {
        final String requestUrl = url;
        final String params = jsonParams;
        OkHttpClient okHttpClient = this.createClient();
        final String authorizationValue = AUTHORIZATION_VALUE;
        String response = "";
        final Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .addHeader(AUTHORIZATION, authorizationValue)
                .post(RequestBody.create(MEDIA_TYPE_JSON, params))
                .build();

        try
        {
            response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (TextUtils.isEmpty(response))
            {
                response = HttpErrorResponse;
            }
        }
        return response;
    }

    private String connectToApiByPut()
    {
        final String requestUrl = url;
        final String params = jsonParams;
        OkHttpClient okHttpClient = this.createClient();
        final String authorizationValue = this.createHeadsValue();
        String response = "";
        final Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .addHeader(AUTHORIZATION, authorizationValue)
                .put(RequestBody.create(MEDIA_TYPE_JSON, params))
                .build();

        try
        {
            response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (TextUtils.isEmpty(response))
            {
                response = HttpErrorResponse;
            }
        }
        return response;
    }

    private String connectToApiByPatch()
    {
        final String requestUrl = url;
        final String params = jsonParams;
        OkHttpClient okHttpClient = this.createClient();
        final String authorizationValue = this.createHeadsValue();
        String response = "";
        final Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .addHeader(AUTHORIZATION, authorizationValue)
                .patch(RequestBody.create(MEDIA_TYPE_JSON, params))
                .build();

        try
        {
            response = okHttpClient.newCall(request).execute().body().string();//同步阻塞

            //okHttpClient.newCall(request).enqueue(new Callback());异步不阻塞

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (TextUtils.isEmpty(response))
            {
                response = HttpErrorResponse;
            }
        }
        return response;

    }

    private Response connectToApiByDownLoad()
    {
        final String requestUrl = url;
        OkHttpClient okHttpClient = this.createClient();
        final String authorizationValue = this.createHeadsValue();
        final Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .addHeader(AUTHORIZATION, authorizationValue)
                .get()
                .build();

        try
        {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful())
            {
                return response;
            } else
            {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private OkHttpClient createClient()
    {
        final OkHttpClient okHttpClient = OkHttpUtils.getInstance().getOkHttpClient();
        if (okHttpClient == null)
        {
            initHttp();
            final OkHttpClient okHttpClientTmp = OkHttpUtils.getInstance().getOkHttpClient();
            return okHttpClientTmp;
        }
        return okHttpClient;
    }

    private String createHeadsValue()
    {
        String heads = AUTHORIZATION_VALUE;
        if (isSecuretVersion)
        {
        }
        return heads;
    }

    public static void initHttp()
    {
        if (!ApiConnection.isSecuretVersion)
        {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new LoggerInterceptor("API",true))
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();

            OkHttpUtils.initClient(okHttpClient);
        } else
        {
            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new LoggerInterceptor("API",true))
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .build();
            OkHttpUtils.initClient(okHttpClient);
        }
    }

}