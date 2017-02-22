package tech.libin.rahul.ideaproject.service.handlers;


import tech.libin.rahul.ideaproject.service.responses.base.FOSError;

/**
 * Created by 10945 on 10/27/2016.
 */

public interface ServiceCallback<T> {
    void onResponse(T response);

    void onRequestTimout();

    void onRequestFail(FOSError error);
}
