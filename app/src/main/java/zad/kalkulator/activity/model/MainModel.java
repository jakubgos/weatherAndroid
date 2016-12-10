package zad.kalkulator.activity.model;

import zad.kalkulator.Data.NetworkInterface;
import zad.kalkulator.activity.Mvp_inter;
import zad.kalkulator.common.Weather;

/**
 * Created by Bos on 2016-12-04.
 */
public class MainModel implements Mvp_inter.PresenterToModel {


    NetworkInterface nNetworkImpl;

    public MainModel(NetworkInterface networkImpl) {
        this.nNetworkImpl = networkImpl;
    }

    @Override
    public void downloadWeatherData(String city, final ModelResult callBack) {
       nNetworkImpl.getWeather(city, new NetworkInterface.NetworkInterfaceResult() {
           @Override
           public void onResultReady(Weather weather) {
               callBack.onwWeatherReady(weather);
           }

           @Override
           public void onResultError(String msg) {
               callBack.onDownloadError(msg);
           }
       });
    }

    public interface ModelResult{
        void onwWeatherReady(Weather weather);
        void onDownloadError(String msg);
    }

}
