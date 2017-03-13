package tech.libin.rahul.ideaproject.configurations;

import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by rahul on 2/26/2017.
 */
public class Config {
    private static Config _config = new Config();
    private User user;
    private Constants.Type tabSelected;

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

    public Constants.Type getTabSelected() {
        return tabSelected;
    }

    public void setTabSelected(Constants.Type tabSelected) {
        this.tabSelected = tabSelected;
    }
}
