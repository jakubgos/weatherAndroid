package zad.kalkulator;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import static android.R.attr.id;



public class WeatherResultFragment extends Fragment {

    public static final String ID_PARAM = "-1";
    private int cityId=-1;

    private TextView temp;
    private TextView name;
    private TextView pressure;
    private TextView country;
    private TextView description;

    Handler handler;

    public WeatherResultFragment(){
        super();
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liner_cacl, container, false);

        if (getArguments() != null) {

            cityId = getArguments().getInt(ID_PARAM);

        }
        Log.d("myLog", "value passed: " +cityId);

        temp = (TextView) view.findViewById(R.id.temp);
        name = (TextView) view.findViewById(R.id.name);
        pressure = (TextView) view.findViewById(R.id.pressure);
        country = (TextView) view.findViewById(R.id.country);
        description = (TextView) view.findViewById(R.id.description);

        Resources res = getResources();


        String[] tab_names = getResources().getStringArray(R.array.cityName);

        updateWeatherData(res.getStringArray(R.array.cityName)[cityId]);
        // Inflate the layout for this fragment
        return view;
    }

    private void updateWeatherData(final String city){
        new Thread(){
            public void run(){
                final JSONObject json = RemoteFetch.getJSON(getContext(), city);
                if(json == null){
                    handler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(getContext(),
                                    "city not found",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable(){
                        public void run(){
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }
    private void renderWeather(JSONObject json){
        try {
            Log.d("data","renderWeather()");

            Log.d("data",json.getString("message"));

            double temper =  json.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp") - 272.15;
            Log.d("data","a" +  String.format("%.2f", temper));

            temp.setText(   String.format("%.2f C", temper)  );

            name.setText(  json.getJSONArray("list").getJSONObject(0).getString("name") );

            pressure.setText(  String.format("%.2f hPa", json.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("pressure")) );
            Log.d("data","asdasdasd" );

            Log.d("data","a" +  json.getJSONArray("list").getJSONObject(0).toString() );
            country.setText( json.getJSONArray("list").getJSONObject(0).getJSONObject("sys").getString("country") );

            description.setText(  json.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description"));

        }catch(Exception e){
            e.printStackTrace();
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }


    }
