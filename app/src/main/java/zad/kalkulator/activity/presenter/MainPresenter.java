package zad.kalkulator.activity.presenter;

import android.os.Bundle;

import java.lang.ref.WeakReference;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import zad.kalkulator.R;
import zad.kalkulator.activity.Mvp_inter;
import zad.kalkulator.common.Weather;

/**
 * Created by Bos on 2016-12-04.
 */
public class MainPresenter implements Mvp_inter.PresenterOps,Mvp_inter.ModelToPresenter {

    // View reference. We use as a WeakReference
    // because the Activity could be destroyed at any time
    // and we don't want to create a memory leak
    private WeakReference<Mvp_inter.ViewOps> mView;
    // Model reference
    private Mvp_inter.PresenterToModel mModel;

    /**
     * Presenter Constructor
     * @param view  MainActivity
     */
    public MainPresenter(Mvp_inter.ViewOps view) {
        mView = new WeakReference<>(view);
    }


    private Mvp_inter.ViewOps  getView() throws NullPointerException{
        if ( mView != null )
            return mView.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    /**
     * Called by Activity during MVP setup. Only called once.
     * @param model Model instance
     */
    public void setModel(Mvp_inter.PresenterToModel model) {
        mModel = model;

    }

    @Override
    public void onStartup(Bundle savedInstanceState) {
        if(savedInstanceState == null) {
            getView().showMainMenu();
        }

    }

    @Override
    public void onCitySelected(final int id) {
        getView().showProgressBar();
        getView().hideListView();

        Observable<Weather> fetchFromGoogle = Observable.create(new Observable.OnSubscribe<Weather>() {
            @Override
            public void call(Subscriber<? super Weather> subscriber) {
                try {
                    Weather data =  mModel.downloadWeatherData(getView().getViewResources().getStringArray(R.array.cityName)[id]);
                    subscriber.onNext(data); // Emit the contents of the URL
                    subscriber.onCompleted(); // Nothing more to emit
                }catch(Exception e){
                    subscriber.onError(e); // In case there are network errors
                }
            }
        });

        fetchFromGoogle
                .subscribeOn(Schedulers.newThread()) // Create a new Thread
                .observeOn(AndroidSchedulers.mainThread()) // Use the UI thread
                .subscribe(new Action1<Weather>() {
                    @Override
                    public void call(Weather weather) {
                        handleWeatherResult(weather);
                    }
                });
            }

    private void handleWeatherResult(Weather weather) {
        getView().hideProgressBar();
        getView().loadResultFragment(weather);
    }

    @Override
    public Weather returnWeatherData() {
        return null;
    }
}
