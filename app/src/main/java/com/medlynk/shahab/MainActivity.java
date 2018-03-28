package com.medlynk.shahab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.medlynk.shahab.myviewselection.ViewSelection;
import com.medlynk.shahab.viewselection.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        ViewSelection.OnSingleItemSelectedListener,
        ViewSelection.OnMultiItemSelectedListener,
        ViewSelection.OnClearStateListener,
        ViewSelection.OnHelpfullyOptionsClickListener {

    ViewSelection buttons;
    ViewSelection buttons2;
    Button button;
    private List<Answer> answers = new ArrayList<> ();

    String text[] = {"shahab", "mammad", "shakib"};
    private List<Integer> selectedItems = new ArrayList<> ();
    private int helpfully_option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        button = findViewById ( R.id.button );
        button.setOnClickListener ( new OnButtonClickListener () );
        button.setEnabled ( false );
        buttons = findViewById ( R.id.buttons );
        buttons2 = findViewById ( R.id.buttons2 );
        buttons2.setOnHelpfullyOptionClickListener ( this );
        buttons.setOnSingleItemSelectedListener ( this );
        buttons2.setOnClearStateListener ( this );
        buttons2.setOnSingleItemSelectedListener ( this );
        buttons.setOnMultiItemSelectedListener ( this );
        buttons.setOnClearStateListener ( this );
        buttons2.setOnMultiItemSelectedListener ( this );
        for (int i = 0; i < buttons.getNumberOfViews (); i++) {
            buttons.setTextToButtons ( text[i], i );
        }
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
        int i = position;
        setAnswerChoices ( i );
        selectedItems.add ( position );
    }

    private void setAnswerChoices(int i) {
        Answer answer = new Answer ();
        switch (i) {
            case 0: {
                answer.setChoice ( "a" );

                break;
            }
            case 1: {
                answer.setChoice ( "b" );

                break;
            }
            case 2: {
                answer.setChoice ( "c" );

                break;
            }
        }
        answers.add ( answer );
        if (answers.size () == 1) {
            button.setEnabled ( true );
        }
    }

    @Override
    public void onMultiItemDeselected(View view, Integer position) {
        System.out.println ( "MainActivity.onMultiItemDeselected" );
        int i = position;
        Iterator<Answer> answerIterator = answers.iterator ();
        switch (i) {
            case 0: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "a" )) {
                        answerIterator.remove ();
                    }
                }
                break;
            }
            case 1: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "b" )) {
                        answerIterator.remove ();
                    }
                }

                break;
            }
            case 2: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "c" )) {
                        answerIterator.remove ();
                    }
                }

                break;
            }
        }
        if (answers.size () == 0) {
            button.setEnabled ( false );
        }
    }

    @Override
    public void onClearState(View view) {
        System.out.println ( "MainActivity.onClearState" );
    }

    @Override
    public void onHelpFullyClicked(int position,
                                   int helpfully_option) {
        System.out.println ( "MainActivity.onHelpFullyClicked" );
        System.out.println ( "position = [" + position + "], helpfully_option = [" + helpfully_option + "]" );
        Iterator<Answer> answerIterator = answers.iterator ();
        switch (position) {
            case 0: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "a" )) {
                        answer.setHelpfully ( helpfully_option );
                        break;
                    }
                }
                break;
            }
            case 1: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "b" )) {
                        answer.setHelpfully ( helpfully_option );
                        break;
                    }
                }

                break;
            }
            case 2: {
                while (answerIterator.hasNext ()) {
                    Answer answer = answerIterator.next ();
                    if (answer.getChoice () != null && answer.getChoice ().equals ( "c" )) {
                        answer.setHelpfully ( helpfully_option );
                        break;
                    }
                }

                break;
            }
        }
    }

    private class OnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println ( "OnButtonClickListener.onClick" );
            if (answers.size () > 0) {
                boolean hasError = false;
                for (int i = 0; i < answers.size (); i++) {
                    if (answers.get ( i ).getHelpfully () == -1) {
                        buttons2.showHelpfullyOptionError ( i, View.VISIBLE );
                        hasError = !hasError;
                        break;
                    }
                }

                if (!hasError) {
                    Toast.makeText ( MainActivity.this, "perfect", Toast.LENGTH_SHORT ).show ();
                }
            }
        }
    }
}
