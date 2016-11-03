package zad.kalkulator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainFragActivity extends AppCompatActivity implements MainMenuFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frag);


    }

    @Override
    public void onFragmentInteraction(int value) {
    Log.d("test", "ni ma szans.!");


    }
}
