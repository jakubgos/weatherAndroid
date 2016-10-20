package zad.kalkulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void changeToLinear(View view) {
        Intent intent = new Intent(this, LinearCalcActivity.class);
        startActivity(intent);
    }



    public void changeToRelative(View view) {
        Intent intent = new Intent(this, RelativeCaclActivity.class);
        startActivity(intent);
    }

    public void printAbout(View view) {
        Toast.makeText(getApplicationContext(), "Author: JakubJGos", Toast.LENGTH_SHORT).show();

    }

    public void doExit(View view) {
        finish();
        System.exit(0);
    }
}
