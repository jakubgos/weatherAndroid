package zad.kalkulator.activity.model;

import zad.kalkulator.Data.NetworkInterface;
import zad.kalkulator.activity.Mvp_inter;
import zad.kalkulator.common.Weather;

/**
 * Created by Bos on 2016-12-04.
 */
public class MainModel implements Mvp_inter.PresenterToModel {


    private Mvp_inter.PresenterOps mPresenter;
    NetworkInterface.NetworkReq nNetworkImpl;


    public MainModel(Mvp_inter.PresenterOps presenter, NetworkInterface.NetworkReq networkImpl) {
        this.mPresenter = presenter;
        this.nNetworkImpl = networkImpl;

    }


    @Override
    public Weather downloadWeatherData(String city) {
       return nNetworkImpl.getWeather(city);
    }


}
