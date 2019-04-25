package ru.neooffline.homeworka1l5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondScreen extends AppCompatActivity {

    static final String TOKEN = "weatherObj";
    static final String IS_CHECKED_PARAM = "isChecked";
    static final int STRINGS = 3;
    private TextView[][] tTopics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        Intent intent = this.getIntent();
        Weather weather;
        try {
            weather = intent.getParcelableExtra(TOKEN);
        } catch (Throwable t) {
            Log.e("Error", t.getMessage());
            weather = new Weather(true);
        }
        settTopics();
        for (int j = 0; j < STRINGS; j++) {
            if (intent.getExtras().getBoolean(String.format("%s%s", IS_CHECKED_PARAM, String.valueOf(j)))) {
                tTopics[j][1].setText(String.format("%d", weather.getAllValues()[j]));
            } else {
                for (int k = 0; k < STRINGS; k++) {
                    tTopics[j][k].setVisibility(View.INVISIBLE);
                }
            }
        }

    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    void settTopics() {
        tTopics = new TextView[STRINGS][STRINGS];
        tTopics[0][0] = findViewById(R.id.temp_value);
        tTopics[1][0] = findViewById(R.id.hum_value);
        tTopics[2][0] = findViewById(R.id.press_value);
        tTopics[0][1] = findViewById(R.id.ss_temp_value);
        tTopics[1][1] = findViewById(R.id.ss_hum_value);
        tTopics[2][1] = findViewById(R.id.ss_press_value);
        tTopics[0][2] = findViewById(R.id.temp_dim);
        tTopics[1][2] = findViewById(R.id.hum_dim);
        tTopics[2][2] = findViewById(R.id.press_dim);
    }
}
