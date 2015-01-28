package com.raha.exercise2.tasks;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.raha.exercise2.enums.Gender;
import com.raha.exercise2.interfaces.LoadDataInt;
import com.raha.exercise2.models.User;
import com.raha.exercise2.utils.Consts;

/**
 * Created by raha on 2015-01-28.
 */
public class LoadData extends AsyncTask<Integer, Integer, User> {
    private SharedPreferences shPreferences;
    private LoadDataInt loadData;

    public LoadData(SharedPreferences shPreferences, LoadDataInt loadData) {
        this.shPreferences = shPreferences;
        this.loadData = loadData;
    }

    @Override
    protected User doInBackground(Integer... params) {
        String name = shPreferences.getString(Consts.KEY_NAME, null);
        String sc_name = shPreferences.getString(Consts.KEY_SEC_NAME, null);
        boolean married = shPreferences.getBoolean(Consts.KEY_MARRIED, false);
        String genderStr = shPreferences.getString(Consts.KEY_GENDER, null);
        Gender gender;
        if (Gender.MALE.toString().equals(genderStr)) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }
        int day = shPreferences.getInt(Consts.KEY_DAY, 0);
        int month = shPreferences.getInt(Consts.KEY_MONTH, 0);
        int year = shPreferences.getInt(Consts.KEY_YEAR, 0);
        return new User(name, sc_name, married, gender, day, month, year);
    }

    @Override
    protected void onPostExecute(User user) {
        loadData.getUser(user);
    }
}
