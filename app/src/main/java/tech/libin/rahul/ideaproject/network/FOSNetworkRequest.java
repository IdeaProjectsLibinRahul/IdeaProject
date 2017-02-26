package tech.libin.rahul.ideaproject.network;

import android.net.Uri;

import com.google.gson.JsonDeserializer;

import java.util.Map;

import tech.libin.rahul.ideaproject.network.handlers.NetworkCallback;

/**
 * Created by 10945 on 10/26/2016.
 */

public interface FOSNetworkRequest<T> {
    void request(int method, NetworkCallback<T> callback);

    void request(int method, NetworkCallback<T> callback, JsonDeserializer<T> jsonDeserializer);

    void uploadFile(int method, NetworkCallback<T> callback, final Map<String, String> data, Map<String, Uri> files);
}
