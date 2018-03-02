package com.medlynk.shahab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.viewselection.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        ViewSelection.OnSingleItemSelectedListener,
        ViewSelection.OnMultiItemSelectedListener
        {

    ViewSelection buttons;
    ViewSelection buttons2;
    Button clear;

    String text[] = {"shahab", "mammad", "shakib"};
    private List<Integer> selectedItems = new ArrayList<> (  );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        clear = findViewById ( R.id.button );
        buttons = findViewById ( R.id.buttons );
        buttons2 = findViewById ( R.id.buttons2 );
        buttons.setOnSingleItemSelectedListener ( this );
        buttons2.setOnSingleItemSelectedListener ( this );
        buttons.setOnMultiItemSelectedListener ( this );
        buttons2.setOnMultiItemSelectedListener ( this );
        for (int i = 0 ; i < buttons.getNumberOfViews (); i++){
            buttons.setTextToButtons ( text[i], i );
        }
        clear.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                buttons.setClear ();
                buttons2.setClear ();
            }
        } );
    }

    @Override
    public void onSingleItemSelected(View view, int position) {
        System.out.println ( "MainActivity.onSingleItemSelected" );
        System.out.println ( "view = [" + view + "], position = [" + position + "]" );
    }

    @Override
    public void onMultiItemSelected(View view, Integer position) {
        System.out.println ( "MainActivity.onMultiItemSelected" );
        System.out.println ( "position = [" + position + "]" );
        selectedItems.add ( position );
        for (Integer integer: selectedItems){
            System.out.println ("integer = " + integer);
        }
    }

    @Override
    public void onMultiItemDeselected(View view, Integer position) {
        System.out.println ( "MainActivity.onMultiItemDeselected" );
        selectedItems.remove ( position );
        for (Integer integer: selectedItems){
            System.out.println ("integer = " + integer);
        }
    }
}
