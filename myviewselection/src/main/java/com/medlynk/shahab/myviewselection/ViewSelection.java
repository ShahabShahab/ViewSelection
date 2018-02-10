package com.medlynk.shahab.myviewselection;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shahab on 2/9/2018...
 */

public class ViewSelection extends LinearLayout {

    List<Button> buttons = new ArrayList<> (  );
    List<EditText> editTexts = new ArrayList<> (  );
    int numOfViews = 0;
    Boolean single_select = false;
    private int currentSelection = -1;
    private Drawable selected_state, unselected_state;

    private OnClickListener mOnClickListener = new OnClickListener (){
        @Override
        public void onClick(View view) {
            currentSelection = view.getId ();
            if( single_select ){
                for (Button button : buttons) {
                    if( button.getId () == currentSelection){
                        button.setBackgroundDrawable ( selected_state );
                    }else{
                        button.setBackgroundDrawable ( unselected_state );
                    }
                }
            }else{
                if( buttons.get ( currentSelection ).getBackground ().equals ( selected_state ) )
                    buttons.get ( currentSelection ).setBackgroundDrawable ( unselected_state );
                else{
                    buttons.get ( currentSelection ).setBackgroundDrawable ( selected_state );
                }
            }
        }
    };

    public ViewSelection(Context context) {
        super ( context );
    }

    public ViewSelection(Context context, AttributeSet attrs) {
        super ( context, attrs );
        makeView (context, attrs);
    }

    public ViewSelection(Context context, AttributeSet attrs, int defStyleAttr) {
        super ( context, attrs, defStyleAttr );
        makeView ( context, attrs );
    }

    private void makeView(Context context, AttributeSet attrs) {
        selected_state = context.getResources ().getDrawable ( R.drawable.selected_stated );
        unselected_state = context.getResources ().getDrawable ( R.drawable.unselected_state );
        View view = LayoutInflater.from ( context ).inflate ( R.layout.viewselection_parent_view, this, true );
        LinearLayout linearLayout = view.findViewById ( R.id.parent );
        TypedArray typedArray = context.
                obtainStyledAttributes(attrs, R.styleable.ViewSelection, 0, 0);
        single_select = typedArray.getBoolean ( R.styleable.ViewSelection_single_select, false );
        if( typedArray.hasValue ( R.styleable.ViewSelection_number_of_views)){
            numOfViews = typedArray.getInt ( R.styleable.ViewSelection_number_of_views, 1 );
            if( !typedArray.hasValue ( R.styleable.ViewSelection_button_type ) &&
                    !typedArray.hasValue ( R.styleable.ViewSelection_edittext_type )){
                throw new RuntimeException ( ViewSelection.class.getSimpleName () + " must have a view type!" );
            }else{
                if( typedArray.getBoolean ( R.styleable.ViewSelection_button_type , false) ){
                    for( int i = 0 ; i < typedArray.getInt ( R.styleable.ViewSelection_number_of_views , 1 ); i++){
                        Button button = new Button ( context );
                        button.setBackgroundResource ( typedArray.getResourceId ( R.styleable.ViewSelection_unselected_background, R.drawable.unselected_state ) );
                        LayoutParams layoutParams = new LayoutParams ( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT );
                        layoutParams.setMargins ( 10, 10, 10, 10 );
                        button.setLayoutParams ( layoutParams );
                        button.setId ( i );
                        button.setOnClickListener ( mOnClickListener );
                        buttons.add ( button );
                        linearLayout.addView ( button );
                    }
                }else if ( typedArray.getBoolean ( R.styleable.ViewSelection_edittext_type, false ) ) {
                    for (int i = 0; i < typedArray.getInt ( R.styleable.ViewSelection_number_of_views, 1 ); i++) {
                        EditText editText = new EditText ( context );
                        LayoutParams layoutParams = new LayoutParams ( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
                        layoutParams.setMargins ( 10, 10, 10, 10 );
                        editText.setLayoutParams ( layoutParams );
                        editTexts.add ( editText );
                        linearLayout.addView ( editText );
                    }
                }
            }
        }
    }
    public void setTextToButtons( String text, int position ){
        buttons.get ( position ).setText ( text );
    }
    public void setTextToEditTexts( String text, int position ){
        editTexts.get ( position ).setText ( text );
    }
    public int getNumberOfViews(){
        return numOfViews;
    }
    public int getCurrentSelection() {
        return currentSelection;
    }
}
