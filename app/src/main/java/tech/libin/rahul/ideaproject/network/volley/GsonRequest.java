package tech.libin.rahul.ideaproject.network.volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import tech.libin.rahul.ideaproject.service.requests.base.FOSBaseRequest;

/**
 * Created by 10945 on 10/27/2016.
 */

public class GsonRequest<T> extends Request<T> {
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;
    private Map<String, String> postParams;
    private String postString = null;
    private JsonDeserializer<T> jsonDeserializer = null;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(int method, String url, Class<T> clazz,
                       Map<String, String> headers, Map<String, Object> params,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);

        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;

        if (method == Method.POST && params != null && params.size() > 0) {
            setRetryPolicy(new DefaultRetryPolicy(12000, 0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            postString = new GsonBuilder().create().toJson(params);
        }

    }

    public GsonRequest(int method, String url, Class<T> clazz,
                       Map<String, String> headers, FOSBaseRequest request,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);

        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;

        if (method == Method.POST && request != null) {
            setRetryPolicy(new DefaultRetryPolicy(12000, 0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            postString = new GsonBuilder().create().toJson(request);
            Log.e("Request", postString);
        }

    }

    public void setJsonDeserializer(JsonDeserializer<T> jsonDeserializer) {
        this.jsonDeserializer = jsonDeserializer;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
//        params.put("Content-Type", "application/json");
        params.put("Accept", "application/json");
        return headers != null ? headers : params;

    }

    @Override
    public byte[] getBody() throws AuthFailureError {

        return postString != null ? postString.getBytes(Charset
                .forName("UTF-8")) : super.getBody();
    }

    @Override
    public String getBodyContentType() {
        return postString != null ? "application/json; charset=utf-8" : super.getBodyContentType();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.i("response", json);

            Gson gson;
            if (jsonDeserializer != null) {
                gson = new GsonBuilder().registerTypeAdapter(clazz, jsonDeserializer).create();
            } else {
                gson = new GsonBuilder().create();
            }

            T responses = gson.fromJson(json, clazz);
            return Response.success(responses,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }
}

