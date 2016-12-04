package zad.kalkulator;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.system.ErrnoException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.github.ybq.android.spinkit.SpinKitView;

import org.json.JSONObject;

import zad.kalkulator.common.Weather;


public class WeatherResultFragment extends Fragment {

    public static final String ID_PARAM = "-1";
    private int cityId=-1;

    private TextView temp;
    private TextView name;
    private TextView pressure;
    private TextView country;
    private TextView description;
    View view;
    Handler handler;
    private Weather weather = new Weather();

    public WeatherResultFragment(){
        super();
        handler = new Handler();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_liner_cacl, container, false);

        if (getArguments() != null) {
            cityId = getArguments().getInt(ID_PARAM);
        }

        Log.d("myLog", "value passed: " +cityId);

        temp = (TextView) view.findViewById(R.id.temp);
        name = (TextView) view.findViewById(R.id.name);
        pressure = (TextView) view.findViewById(R.id.pressure);
        country = (TextView) view.findViewById(R.id.country);
        description = (TextView) view.findViewById(R.id.description);

        buildData();
        return view;
    }

    private void buildData(){


            temp.setText(   weather.temp+""  );
            name.setText(  weather.cityName );
            pressure.setText(  weather.pressure );
            country.setText( weather.country );
            description.setText( weather.description);
    }


    public void setObject(Weather weather) {
    this.weather=weather;
    }
}
