package com.medlynk.shahab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.viewselection.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        ViewSelection.OnSingleItemSelectedListener,
        ViewSelection.OnMultiItemSelectedListener{

    ViewSelection buttons;
    String text[] = {"shahab", "mammad", "shakib"};
    private List<Integer> selectedItems = new ArrayList<> (  );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        buttons = findViewById ( R.id.buttons );
        buttons.setOnSingleItemSelectedListener ( this );
        buttons.setOnMultiItemSelectedListener ( this );
        for (int i = 0 ; i < buttons.getNumberOfViews (); i++){
            buttons.setTextToButtons ( text[i], i );
        }
    }

    @Override
    public void onSingleItemSelected(int position) {
        System.out.println ( "MainActivity.onSingleItemSelected" );
        System.out.println ( "position = [" + position + "]" );
    }

    @Override
    public void onMultiItemSelected(Integer position) {
        System.out.println ( "MainActivity.onMultiItemSelected" );
        System.out.println ( "position = [" + position + "]" );
        selectedItems.add ( position );
        for (Integer integer: selectedItems){
            System.out.println ("integer = " + integer);
        }
    }

    @Override
    public void onMultiItemDeselected(Integer position) {
        System.out.println ( "MainActivity.onMultiItemDeselected" );
        selectedItems.remove ( position );
        for (Integer integer: selectedItems){
            System.out.println ("integer = " + integer);
        }
    }
}
