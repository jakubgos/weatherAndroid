package zad.kalkulator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static android.R.attr.id;



public class WeatherResultFragment extends Fragment {

    public static final String ID_PARAM = "-1";
    private int cityId=-1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liner_cacl, container, false);

        if (getArguments() != null) {

            cityId = getArguments().getInt(ID_PARAM);

        }
        Log.d("myLog", "value passed: " +cityId);
        Toast.makeText(getContext(),
                "Id: " + String.valueOf(cityId),
                Toast.LENGTH_SHORT).show();


        // Inflate the layout for this fragment
        return view;
    }
    }
