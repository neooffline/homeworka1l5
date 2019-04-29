package ru.neooffline.homeworka1l5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class CitiesFragment extends ListFragment {
    private static final String KEY = "CurrentCity";
    private boolean isExistCoatOfArms;
    private Weather currentWeather;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.cities,
                android.R.layout.simple_list_item_activated_1);
        setListAdapter(adapter);
        View detailsFrame = getActivity().findViewById(R.id.coat_of_arms);
        isExistCoatOfArms = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        if (savedInstanceState != null)
            currentWeather = savedInstanceState.getParcelable(KEY);
        else
            currentWeather = new Weather(true);
        if(isExistCoatOfArms){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showCoat(currentWeather);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY,currentWeather);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TextView cityNameView = (TextView) view;
        currentWeather= new Weather(position,cityNameView.getText().toString());
        showCoat(currentWeather);
    }
    private void showCoat(Weather weather){
        if(isExistCoatOfArms){
            getListView().setItemChecked(weather.getAllValues()[4],true);
            Coa
        }
    }
}
