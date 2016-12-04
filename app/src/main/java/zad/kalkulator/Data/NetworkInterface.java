package zad.kalkulator.Data;

import android.os.Bundle;

import zad.kalkulator.common.Weather;

/**
 * Created by Bos on 2016-12-04.
 */

public interface NetworkInterface {

    interface NetworkReq {
        Weather getWeather(String city);
    }
}
