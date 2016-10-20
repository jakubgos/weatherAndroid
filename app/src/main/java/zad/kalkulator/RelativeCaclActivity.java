package zad.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RelativeCaclActivity extends AppCompatActivity {
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_cacl);
        t = (TextView) findViewById(R.id.txtScreen);
    }
    public void buttonUse(View view) {

        t.setText(t.getText() + (String) view.getTag());

    }

    public void result(View view) {

        Toast toast = Toast.makeText(getApplicationContext(), "Sorry, You need to buy this feature", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void clear(View view) {
        t.setText(" ");
    }

}
