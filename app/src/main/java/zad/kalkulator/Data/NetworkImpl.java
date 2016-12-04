package zad.kalkulator.Data;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import zad.kalkulator.common.Weather;

/**
 * Created by Bos on 2016-12-04.
 */

public class NetworkImpl implements NetworkInterface.NetworkReq{


    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/find?q=%s";
    private static final int OPEN_WEATHER_APP_ID = 111111;

    @Override
    public Weather getWeather(String city) {
        Weather resultWeather = new Weather();

        StringBuffer json = new StringBuffer(1024);
            Log.d("myLog", "try to send request: " + city);
            URL url = null;
        try {
            url = new URL(String.format(OPEN_WEATHER_MAP_API, city));

        HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.addRequestProperty("x-api-key",
                    "d32a0bb9c5d13290bb3dcca5f2b3883a"); //PO CO TO?

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));


            String tmp = "";
            while ((tmp = reader.readLine()) != null)
                json.append(tmp).append("\n");
            reader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return resultWeather;
        } catch (IOException e) {
            e.printStackTrace();
            return resultWeather;
        }

        //Build data
        Log.d("data","copy data do Weather object");

        try {
            JSONObject data;
            data = new JSONObject(json.toString());
            Log.d("myLog", "response from api: " + data.toString());

            // This value will be 404 if the request was not
            // successful
            if (data.getInt("cod") != 200) {
                Log.e("myLog", "cod != 200" );
            }

            resultWeather.temp = data.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp") - 272.15; //double
            resultWeather.cityName = data.getJSONArray("list").getJSONObject(0).getString("name");//string
            resultWeather.pressure = String.format("%.2f hPa", data.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("pressure")) ;//string
            resultWeather.country = data.getJSONArray("list").getJSONObject(0).getJSONObject("sys").getString("country");
            resultWeather.description = data.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }



        return resultWeather;
    }
}

