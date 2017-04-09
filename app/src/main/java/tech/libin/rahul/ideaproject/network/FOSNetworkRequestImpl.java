package tech.libin.rahul.ideaproject.network;

import android.content.Context;
import android.net.Uri;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonDeserializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import tech.libin.rahul.ideaproject.network.constants.ServiceURLs;
import tech.libin.rahul.ideaproject.network.handlers.NetworkCallback;
import tech.libin.rahul.ideaproject.network.utils.SSLConnection;
import tech.libin.rahul.ideaproject.network.volley.GsonRequest;
import tech.libin.rahul.ideaproject.network.volley.VolleyMultipartRequest;
import tech.libin.rahul.ideaproject.service.requests.base.FOSBaseRequest;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.utils.ApplicationContextHolder;

/**
 * Created by 10945 on 10/27/2016.
 */

public class FOSNetworkRequestImpl<T> implements FOSNetworkRequest<T> {
    private FOSBaseRequest request;
    private String url;
    private Class<T> responseClass;

    public FOSNetworkRequestImpl(String url, Class<T> responseClass) {
        this.url = ServiceURLs.build(url);
        this.responseClass = responseClass;
    }

    public FOSNetworkRequestImpl(FOSBaseRequest request, String url, Class<T> responseClass) {
        this.request = request;
        this.url = ServiceURLs.build(url);
        this.responseClass = responseClass;
    }

    @Override
    public void request(int method, final NetworkCallback<T> callback) {
        Context context = ApplicationContextHolder.getApplicationContext();
        RequestQueue queue = Volley.newRequestQueue(context);
        SSLConnection.allowAllSSL();

        GsonRequest<T> gsonRequest = new GsonRequest<T>(method, url, responseClass, null, request, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                if (error.getClass() == NoConnectionError.class || error.getClass() == TimeoutError.class) {
                    callback.onTimeout();
                } else {
                    FOSError FOSError = new FOSError();
                    FOSError.setErrorMessage(error.getMessage());
                    callback.onFail(FOSError);
                }
            }
        });

        gsonRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 10000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                callback.onTimeout();
            }
        });

        queue.add(gsonRequest);
    }

    @Override
    public void request(int method, final NetworkCallback<T> callback, JsonDeserializer<T> jsonDeserializer) {
        Context context = ApplicationContextHolder.getApplicationContext();
        RequestQueue queue = Volley.newRequestQueue(context);
        SSLConnection.allowAllSSL();

        GsonRequest<T> gsonRequest = new GsonRequest<T>(method, url, responseClass, null, request, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                FOSError FOSError = new FOSError();
                FOSError.setErrorMessage(error.getMessage());
                callback.onFail(FOSError);
            }
        });

        gsonRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 10000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                callback.onTimeout();
            }
        });

        if (jsonDeserializer != null) {
            gsonRequest.setJsonDeserializer(jsonDeserializer);
        }

        queue.add(gsonRequest);
    }

    @Override
    public void uploadFile(int method, final NetworkCallback<T> callback, final Map<String, String> data, final Map<String, Uri> files) {
        final Context context = ApplicationContextHolder.getApplicationContext();
        RequestQueue queue = Volley.newRequestQueue(context);
        SSLConnection.allowAllSSL();

        VolleyMultipartRequest<T> multipartRequest = new VolleyMultipartRequest<T>(Request.Method.POST,  "http://192.9.200.183/IBS.Mvc4/api/Account/Register", responseClass, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                FOSError FOSError = new FOSError();
                FOSError.setErrorMessage(error.getMessage());
                callback.onFail(FOSError);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                if (data != null) {
                    params = data;
                }

                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();

                try {

                    if (files != null) {
                        for (String key : files.keySet()) {
                            Uri uri = files.get(key);

                            InputStream iStream = null;
                            iStream = context.getContentResolver().openInputStream(uri);

                            if (iStream != null) {
                                byte[] inputData = new byte[iStream.available()];
                                iStream.read(inputData);
                                params.put("filename", new DataPart(key, inputData, "image/jpeg"));
                            }

                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

                return params;
            }

        };

        queue.add(multipartRequest);
    }
}
