package com.raha.exercise2.tasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.raha.exercise2.models.User;
import com.raha.exercise2.utils.Consts;

/**
 * Created by raha on 2015-01-28.
 */
public class SaveData extends AsyncTask<User, Integer,Boolean> {
    private SharedPreferences shPreferences;

    public SaveData(SharedPreferences shPreferences) {
        this.shPreferences = shPreferences;

    }

    @Override
    protected Boolean doInBackground(User... params) {
        User user = params[0];
        SharedPreferences.Editor editor = shPreferences.edit();
        editor.putString(Consts.KEY_NAME, user.getFirstNameText());
        editor.putString(Consts.KEY_SEC_NAME, user.getSecondNameText());
        editor.putBoolean(Consts.KEY_MARRIED, user.isMarriedChecked());
        editor.putString(Consts.KEY_GENDER, user.getGender().toString());
        editor.putInt(Consts.KEY_DAY, user.getDay());
        editor.putInt(Consts.KEY_MONTH, user.getMonth());
        editor.putInt(Consts.KEY_YEAR, user.getYear());
        editor.apply();
        return null;
    }
}
