package com.medlynk.shahab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.viewselection.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewSelection.OnSingleItemSelectedListener, ViewSelection.OnMultiItemSelectedListener, ViewSelection.OnClearStateListener {


    ViewSelection viewSelection;
    Button button;
    List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewSelection = findViewById(R.id.viewselection);
        viewSelection.setOnMultiItemSelectedListener(this);
        viewSelection.setOnClearStateListener(this);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("MainActivity.onClick");
                viewSelection.setClear();
            }
        });
    }

    @Override
    public void onSingleItemSelected(View view, int position) {
        System.out.println(view.getId());
        System.out.println(position);
    }

    @Override
    public void onMultiItemSelected(View view, Integer position) {
        System.out.println("MainActivity.onMultiItemSelected");
        System.out.println("view = [" + view + "], position = [" + position + "]");
        list.add(position);
    }

    @Override
    public void onMultiItemDeselected(View view, Integer position) {
        System.out.println("MainActivity.onMultiItemDeselected");
        System.out.println("view = [" + view + "], position = [" + position + "]");
        list.remove(position);
    }

    @Override
    public void onClearState(View view) {
        System.out.println("MainActivity.onClearState");
        System.out.println("view = [" + view + "]");
        list.clear();
    }
}