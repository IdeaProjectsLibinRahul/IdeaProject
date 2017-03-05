package tech.libin.rahul.ideaproject.configurations;

import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by rahul on 2/26/2017.
 */
public class Config {
    private static Config _config = new Config();
    private User user;
    private String sessionKey;

    private Config() {
    }

    public static Config getInstance() {
        return _config;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
