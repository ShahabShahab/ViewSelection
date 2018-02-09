package tk.medlynk.shahab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tk.medlynk.shahab.viewselection.R;

public class MainActivity extends AppCompatActivity {

    ViewSelection buttons;
    String text[] = {"shahab", "mammad", "shakib"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        buttons = findViewById ( R.id.buttons );
        for (int i = 0 ; i < buttons.getNumberOfViews (); i++){
            buttons.setTextToButtons ( text[i], i );
        }
    }
}
