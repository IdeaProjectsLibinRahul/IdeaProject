package tech.libin.rahul.ideaproject.service.mapper.base;

/**
 * Created by libin on 01/03/17.
 */

public interface FOSBaseMapper<G, T, U, V> {

    T getRequest(G model);

    V getResponse(U model);
}
