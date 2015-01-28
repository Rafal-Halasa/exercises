package com.raha.exercise2.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.raha.exercise2.R;
import com.raha.exercise2.enums.Gender;
import com.raha.exercise2.interfaces.LoadDataInt;
import com.raha.exercise2.models.User;
import com.raha.exercise2.tasks.LoadData;
import com.raha.exercise2.tasks.SaveData;
import com.raha.exercise2.utils.Consts;


public class MainActivity extends ActionBarActivity {

    private EditText firstName;
    private EditText secondName;
    private CheckBox married;
    private RadioButton male;
    private RadioButton female;
    private DatePicker date;
    private SharedPreferences shPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        initializeShPreferences();
    }

    private void initializeShPreferences() {
        shPreferences = getSharedPreferences(Consts.MY_PREFERENCE, Context.MODE_PRIVATE);
    }

    private void initializeViews() {
        firstName = (EditText) findViewById(R.id.et_first_name);
        secondName = (EditText) findViewById(R.id.et_second_name);
        married = (CheckBox) findViewById(R.id.cb_married);
        male = (RadioButton) findViewById(R.id.rb_male);
        female = (RadioButton) findViewById(R.id.rb_female);
        date = (DatePicker) findViewById(R.id.dp_date);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveData(View view) {
        String firstNameText = firstName.getText().toString();
        String secondNameText = secondName.getText().toString();
        boolean marriedChecked = married.isChecked();
        Gender gender = getGenderFromRadio();
        int day = date.getDayOfMonth();
        int month = date.getMonth();
        int year = date.getYear();

        if (isBlankNameAndSecondName(firstNameText, secondNameText)) {
            new AlertDialog.Builder(this).setMessage(getString(R.string.error_blank_fields)).show();
        } else {
            User user = new User(firstNameText, secondNameText, marriedChecked, gender, day, month, year);
            new SaveData(shPreferences).execute(user);
        }
    }


    private boolean isBlankNameAndSecondName(String firstNameText, String secondNameText) {
        if (firstNameText.equals("") && secondNameText.equals("")) {
            return true;
        }
        return false;
    }

    LoadDataInt loadDataInt = new LoadDataInt() {
        @Override
        public void getUser(User user) {
            firstName.setText(user.getFirstNameText());
            secondName.setText(user.getSecondNameText());
            married.setChecked(user.isMarriedChecked());
            if(user.getGender().equals(Gender.FEMALE)){
                female.setChecked(true);
            }else{
                male.setChecked(true);
            }
            date.updateDate(user.getYear(),user.getMonth(),user.getDay());
        }
    };

    public void loadData(View view) {
        new LoadData(shPreferences, loadDataInt).execute();
    }

    public Gender getGenderFromRadio() {
        Gender gender = null;
        if (male.isChecked()) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }
        return gender;
    }
}
