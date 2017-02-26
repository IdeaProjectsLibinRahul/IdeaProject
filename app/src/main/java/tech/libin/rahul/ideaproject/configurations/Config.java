package tech.libin.rahul.ideaproject.configurations;

import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by rahul on 2/26/2017.
 */
public class Config {
    private static Config _config = new Config();
    private User user;

    public static Config getInstance() {
        return _config;
    }

    private Config() {
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }
}
