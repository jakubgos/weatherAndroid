package zad.kalkulator.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import zad.kalkulator.common.Weather;

/**
 * Created by Bos on 2016-12-04.
 */

public interface Mvp_inter {


    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     *      Presenter to View
     */
    interface ViewOps {
       // Context getAppContext();
        //Context getActivityContext();
        void showMainMenu();
        void showProgressBar();
        void hideProgressBar();
        void showListView();
        void hideListView();

        Resources getViewResources();
        void loadResultFragment(Weather weather);
        void makeToast(String Text);
    }

    /**
     * Operations offered to View to communicate with Presenter.
     * Process user interaction, sends data requests to Model, etc.
     *      View to Presenter
     */
    interface PresenterOps {
        void onStartup(Bundle savedInstanceState);
        void onCitySelected(int id);
    }

    /**
     * Required Presenter methods available to Model.
     *      Model to Presenter
     */
    interface ModelToPresenter {
        Weather returnWeatherData();
    }

    /**
     * Operations offered to Model to communicate with Presenter
     * Handles all data business logic.
     *      Presenter to Model
     */
    interface PresenterToModel {
        Weather downloadWeatherData(String city);
    }
}
