package zad.kalkulator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainMenuFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frag);

        Fragment fragment = new MainMenuFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }


    @Override
    public void onCitySelected(int id) {
        Fragment fragment = new WeatherResultFragment();


        FragmentManager fm = getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putInt(WeatherResultFragment.ID_PARAM, id);
        fragment.setArguments(args);

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();



    }

}
