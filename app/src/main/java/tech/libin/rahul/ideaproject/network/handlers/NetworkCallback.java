package tech.libin.rahul.ideaproject.network.handlers;


import tech.libin.rahul.ideaproject.service.responses.base.FOSError;

/**
 * Created by 10945 on 10/27/2016.
 */

public interface NetworkCallback<T> {
    void onSuccess(T response);

    void onTimeout();

    void onFail(FOSError error);
}
