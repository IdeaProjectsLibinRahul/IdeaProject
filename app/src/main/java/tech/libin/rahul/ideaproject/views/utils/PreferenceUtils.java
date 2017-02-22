package tech.libin.rahul.ideaproject.views.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import tech.libin.rahul.ideaproject.service.models.UserModel;

/**
 * Created by 10945 on 11/3/2016.
 */

public class PreferenceUtils {

    private static final String PREF_NAME = "FOSAPPLICATION";
    private static final String USER = "USER";
    private static final String DEFAULT_STRING = "NULL";

    private SharedPreferences preferences;

    private PreferenceUtils(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public static PreferenceUtils getInstance(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return new PreferenceUtils(preferences);
    }

    public boolean isUserRegistered() {
        String data = preferences.getString(USER, DEFAULT_STRING);

        if (data.equals(DEFAULT_STRING)) {
            return false;
        }
        UserModel userModel = new Gson().fromJson(data, UserModel.class);
        return userModel != null && userModel.getId() != null;
    }

    public Long getUserId() {
        String data = preferences.getString(USER, DEFAULT_STRING);

        if (data.equals(DEFAULT_STRING)) {
            return null;
        }

        UserModel userModel = new Gson().fromJson(data, UserModel.class);
        return userModel.getId();
    }

    public void saveUser(UserModel userModel) {
        String date = new Gson().toJson(userModel);
        preferences.edit().putString(USER, date).apply();
    }

    public UserModel getUser() {
        String data = preferences.getString(USER, DEFAULT_STRING);

        if (data.equals(DEFAULT_STRING)) {
            return null;
        }

        return new Gson().fromJson(data, UserModel.class);
    }

    public boolean isProfileUpdate() {
        String data = preferences.getString(USER, DEFAULT_STRING);

        if (data.equals(DEFAULT_STRING)) {
            return false;
        }

        UserModel userModel = new Gson().fromJson(data, UserModel.class);
        return !(userModel.getUserName() == null && userModel.getEmail() == null);

    }
}
