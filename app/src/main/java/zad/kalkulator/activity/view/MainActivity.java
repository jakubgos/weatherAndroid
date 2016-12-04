package zad.kalkulator.activity.view;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;

import zad.kalkulator.Data.NetworkImpl;
import zad.kalkulator.MainMenuFragment;
import zad.kalkulator.R;
import zad.kalkulator.WeatherResultFragment;
import zad.kalkulator.activity.Mvp_inter;
import zad.kalkulator.activity.model.MainModel;
import zad.kalkulator.activity.presenter.MainPresenter;
import zad.kalkulator.common.Weather;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity implements Mvp_inter.ViewOps,MainMenuFragment.OnFragmentInteractionListener {


    private Mvp_inter.PresenterOps mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupMVP();
        mPresenter.onStartup(savedInstanceState);
    }

    private void setupMVP() {
        // Check if StateMaintainer has been created
        //if (mStateMaintainer.firstTimeIn()) {
            // Create the Presenter
            MainPresenter presenter = new MainPresenter(this);
            // Create the Model
            NetworkImpl networkImpl = new NetworkImpl();
            MainModel model = new MainModel(presenter, networkImpl);
            // Set Presenter model
            presenter.setModel(model);
            // Add Presenter and Model to StateMaintainer
           // mStateMaintainer.put(presenter);
            //mStateMaintainer.put(model);

            // Set the Presenter as a interface
            // To limit the communication with it
            mPresenter = presenter;

     //   }
        // get the Presenter from StateMaintainer
     //   else {
            // Get the Presenter
      //      mPresenter = mStateMaintainer.get(MainPresenter.class.getName());
            // Updated the View in Presenter
      //      mPresenter.setView(this);
    //    }
    }

    @Override
    public void showMainMenu() {
        Fragment fragment = new MainMenuFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();


    }

    @Override
    public void showProgressBar() {
        findViewById(R.id.spin_kit).setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
            findViewById(R.id.spin_kit).setVisibility(View.GONE);
    }

    @Override
    public void showListView() {
            findViewById(R.id.listView).setVisibility(View.VISIBLE);
        }


    @Override
    public void hideListView() {
        findViewById(R.id.listView).setVisibility(View.GONE);
    }



        @Override
    public Resources getViewResources() {
        return getResources();
    }


    @Override
    public void loadResultFragment(Weather weather) {
        WeatherResultFragment fragment = new WeatherResultFragment();


        FragmentManager fm = getSupportFragmentManager();

        fragment.setObject(weather);

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }

    @Override
    public void onCitySelected(int id) {
        mPresenter.onCitySelected(id);
    }

    @Override
    public void makeToast(String t) {
        Toast.makeText(this,
                t,
                Toast.LENGTH_LONG).show();

    }
}
