package zad.kalkulator;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONObject;

import static android.R.attr.id;



public class WeatherResultFragment extends Fragment {

    public static final String ID_PARAM = "-1";
    private int cityId=-1;

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
        Toast.makeText(getContext(),
                "Id: " + String.valueOf(cityId),
                Toast.LENGTH_SHORT).show();


        updateWeatherData("London");
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
                    Log.d("data",json.getString("name") +
                            ", " +
                            json.getJSONObject("sys").getString("country"));

                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    Log.d("data",details.getString("description") +
                                    "\n" + "Humidity: " + main.getString("humidity") + "%" +
                                    "\n" + "Pressure: " + main.getString("pressure") + " hPa");

                    Log.d("data",String.format("%.2f", main.getDouble("temp"))+ " ℃");



//            cityField.setText(json.getString("name").toUpperCase(Locale.US) +
//                    ", " +
//                    json.getJSONObject("sys").getString("country"));
//
//            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
//            JSONObject main = json.getJSONObject("main");
//            detailsField.setText(
//                    details.getString("description").toUpperCase(Locale.US) +
//                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
//                            "\n" + "Pressure: " + main.getString("pressure") + " hPa");
//
//            currentTemperatureField.setText(
//                    String.format("%.2f", main.getDouble("temp"))+ " ℃");
//
//            DateFormat df = DateFormat.getDateTimeInstance();
//            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
//            updatedField.setText("Last update: " + updatedOn);
//
//            setWeatherIcon(details.getInt("id"),
//                    json.getJSONObject("sys").getLong("sunrise") * 1000,
//                    json.getJSONObject("sys").getLong("sunset") * 1000);

        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }


    }
