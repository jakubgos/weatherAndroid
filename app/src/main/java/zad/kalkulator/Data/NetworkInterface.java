package zad.kalkulator.Data;

import zad.kalkulator.common.Weather;

/**
 * Created by Bos on 2016-12-04.
 */

public interface NetworkInterface {

    void getWeather(String city, NetworkInterfaceResult networkInterfaceResult);


    interface NetworkInterfaceResult {
        void onResultReady(Weather weather);
        void onResultError(String msg);
    }

}
