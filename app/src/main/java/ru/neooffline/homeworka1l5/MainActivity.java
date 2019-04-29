package ru.neooffline.homeworka1l5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Weather weather;
    private SharedPreferences spf;
    private boolean[] booleans;
    private CheckBox[] allCheckBoxies;
    static final int CHECKBOX_NUMBER = 3;
    static final String KEY = "CurrentCity";
    static final String TOKEN = "weatherObj";
    static final String PREF_SAVE = "SAVED_WEATHER";
    static final String PREF_SAVE_B = "PREF_SAVE_B";
    static final String IS_CHECKED_PARAM = "isChecked";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY, weather);
        for (int i = 0; i < allCheckBoxies.length; i++) {
            outState.putBoolean(IS_CHECKED_PARAM + i, allCheckBoxies[i].isChecked());
        }
        makeToastNLog("App Save Instance");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        weather = savedInstanceState.getParcelable(KEY);
        for (int i = 0; i < allCheckBoxies.length; i++) {
            allCheckBoxies[i].setChecked(savedInstanceState.getBoolean(IS_CHECKED_PARAM + i));
        }
        makeToastNLog("App Restore Instance");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCheckBoxies();
        weather = new Weather(true);
        if (savedInstanceState != null) {
            for (int i = 0; i < allCheckBoxies.length; i++) {
                allCheckBoxies[i].setChecked(savedInstanceState.getBoolean(IS_CHECKED_PARAM + i));
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadText();
        makeToastNLog("App Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        chekes();
        saveText();
        makeToastNLog("App onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        chekes();
        makeToastNLog("App Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        chekes();
        saveText();
        makeToastNLog("App Destroyed");
    }

    private void makeToastNLog(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.d("LifeCycle", message);
    }

    public void goToSecond(View view) {
        weather.changeAll();
        Intent intent = new Intent(this.getApplicationContext(), SecondScreen.class);
        intent.putExtra(TOKEN, weather);
        chekes();
        for (int i = 0; i < CHECKBOX_NUMBER; i++) {
            String param = String.format("%s%s", IS_CHECKED_PARAM, i);
            intent.putExtra(param, allCheckBoxies[i].isChecked());
        }
        /*intent.putExtra("isCheckedParam0",true);
        intent.putExtra("isCheckedParam1",true);
        intent.putExtra("isCheckedParam2",true);*/
        startActivity(intent);
    }

    private void saveText() {
        spf = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putString(PREF_SAVE, weather.getFullWeather());
        chekes();
        for (int i = 0; i < allCheckBoxies.length; i++) {
            ed.putBoolean(PREF_SAVE_B + i, allCheckBoxies[i].isChecked());
        }
        ed.apply();
        makeToastNLog("Saving Weather");
    }

    private void loadText() {
        spf = getPreferences(MODE_PRIVATE);
        for (int i = 0; i < CHECKBOX_NUMBER; i++) {
            allCheckBoxies[i].setChecked(spf.getBoolean(PREF_SAVE_B + i, false));
        }
        makeToastNLog("Loading Weather");
    }

    private void chekes() {
        booleans = new boolean[CHECKBOX_NUMBER];
        for (int i = 0; i < CHECKBOX_NUMBER; i++) {
            booleans[i] = allCheckBoxies[i].isChecked();
        }
    }

    private void setCheckBoxies() {
        allCheckBoxies = new CheckBox[CHECKBOX_NUMBER];
        allCheckBoxies[0] = this.findViewById(R.id.check_temp);
        allCheckBoxies[1] = this.findViewById(R.id.check_hum);
        allCheckBoxies[2] = this.findViewById(R.id.check_pres);
    }
}
