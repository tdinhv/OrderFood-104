package com.luuva.background;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.luuva.model.User;

/**
 * Created by Administrator on 10/1/2016.
 */

public class UserSession {
    private static final String TAG = UserSession.class.getSimpleName();
    private static final String PREF_NAME = "login";
    private static final String KEY_IS_LOGGED_IN = "isloggedin";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public UserSession(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean isLoggedin, User user){
        Gson gson = new Gson();
        String usersession = gson.toJson(user);
        editor.putString("userLogin", usersession);
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedin);
        //editor.apply();
        editor.commit();
    }
    public User getUserLogin(){
        Gson gson = new Gson();
        String json = prefs.getString("userLogin", "");
        User obj =new User();
        if(json!=null)
            obj= gson.fromJson(json, User.class);
        return obj;
    }
    public boolean delUserLoginSession(Context ctx){
        prefs= ctx.getSharedPreferences("login",0);
        if(prefs!=null){
            prefs.edit().remove("userLogin").commit();
            return true;
        }
        return false;

    }
    public boolean isUserLoggedin(){return prefs.getBoolean(KEY_IS_LOGGED_IN, false);}
}
