package ru.neooffline.homeworka1l5;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;
import java.util.Random;

public class Weather implements ChangeValue, Parcelable {
    private int temperature;
    private int humidity;
    private int pressure;
    static private final int PARAMETERS = 3;
    private int[] allValues = new int[PARAMETERS];

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    int[] getAllValues() {
        allValues[0] = temperature;
        allValues[1] = humidity;
        allValues[2] = pressure;
        return allValues;
    }

    private String fullWeather;

    Weather(boolean isFull) {
        if (isFull) {
            changeAll();
        } else {
            fullWeather = "Нет данных по погоде";
        }
    }

    private Weather(Parcel in) {
        this.temperature = in.readInt();
        this.humidity = in.readInt();
        this.pressure = in.readInt();
        this.fullWeather = in.readString();
        this.allValues = in.createIntArray();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public void setFullWeather() {
        changeTemp();
        changeHumidity();
        this.fullWeather = String.format(Locale.ENGLISH, "Температура: %d С\u00b0\nВлажность: %d %%",
                this.temperature, this.humidity);
    }

    String getFullWeather() {
        return fullWeather;
    }

    @Override
    public void changeTemp() {
        temperature = getRandomNumberInRange(-10, 26);
    }

    @Override
    public void changePres() {
        pressure = getRandomNumberInRange(650, 750);
    }

    @Override
    public void changeHumidity() {
        humidity = getRandomNumberInRange(20, 90);
    }

    void changeAll() {
        changeTemp();
        changeHumidity();
        changePres();
        allValues[0] = temperature;
        allValues[1] = humidity;
        allValues[2] = pressure;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.temperature);
        dest.writeInt(this.humidity);
        dest.writeInt(this.pressure);
        dest.writeString(this.fullWeather);
        dest.writeIntArray(this.allValues);
    }


}
