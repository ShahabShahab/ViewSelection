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
    private int selected_text_color, unselected_text_color;
    private OnSingleItemSelectedListener onSingleItemSelectedListener;
    private OnMultiItemSelectedListener onMultiItemSelectedListener;

    public OnMultiItemSelectedListener getOnMultiItemSelectedListener() {
        return onMultiItemSelectedListener;
    }

    public void setOnMultiItemSelectedListener(OnMultiItemSelectedListener onMultiItemSelectedListener) {
        this.onMultiItemSelectedListener = onMultiItemSelectedListener;
    }

    public OnSingleItemSelectedListener getOnSingleItemSelectedListener() {
        return onSingleItemSelectedListener;
    }

    public void setOnSingleItemSelectedListener(OnSingleItemSelectedListener onSingleItemSelectedListener) {
        this.onSingleItemSelectedListener = onSingleItemSelectedListener;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public List<EditText> getEditTexts(){
        return editTexts;
    }

    public void setEditTexts(List<EditText> editTexts) {
        this.editTexts = editTexts;
    }

    public int getNumOfViews() {
        return numOfViews;
    }

    public void setNumOfViews(int numOfViews) {
        this.numOfViews = numOfViews;
    }

    public Boolean getSingle_select() {
        return single_select;
    }

    public void setSingle_select(Boolean single_select) {
        this.single_select = single_select;
    }

    public void setCurrentSelection(int currentSelection) {
        this.currentSelection = currentSelection;
    }

    public Drawable getSelected_state() {
        return selected_state;
    }

    public void setSelected_state(Drawable selected_state) {
        this.selected_state = selected_state;
    }

    public Drawable getUnselected_state() {
        return unselected_state;
    }

    public void setUnselected_state(Drawable unselected_state) {
        this.unselected_state = unselected_state;
    }

    private OnClickListener button_click_listener = new OnClickListener (){
        @Override
        public void onClick(View view) {
            currentSelection = view.getId ();
            if( single_select ){
                onSingleItemSelectedListener.onSingleItemSelected ( currentSelection );
                for (Button button : buttons) {
                    if( button.getId () == currentSelection){
                        button.setTextColor ( selected_text_color );
                        button.setBackgroundDrawable ( selected_state );
                    }else{
                        button.setBackgroundDrawable ( unselected_state );
                        button.setTextColor ( unselected_text_color );
                    }
                }
            }else{
                if( buttons.get ( currentSelection ).getBackground ().equals ( selected_state ) ) {
                    buttons.get ( currentSelection ).setBackgroundDrawable ( unselected_state );
                    buttons.get ( currentSelection ).setTextColor (unselected_text_color);
                    onMultiItemSelectedListener.onMultiItemDeselected ( currentSelection );
                }else{
                    onMultiItemSelectedListener.onMultiItemSelected ( currentSelection );
                    buttons.get ( currentSelection ).setBackgroundDrawable ( selected_state );
                    buttons.get ( currentSelection ).setTextColor ( selected_text_color );
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
        selected_text_color = typedArray.getInt ( R.styleable.ViewSelection_selected_text_color, context.getResources ().getColor ( R.color.selected_text_clolor ) );
        unselected_text_color = typedArray.getInt ( R.styleable.ViewSelection_unselected_text_color, context.getResources ().getColor ( R.color.unselected_text_color ) );
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
                        button.setOnClickListener ( button_click_listener );
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

    public interface OnSingleItemSelectedListener {
        void onSingleItemSelected(int position );
    }
    public interface OnMultiItemSelectedListener{
        void onMultiItemSelected(Integer position);
        void onMultiItemDeselected(Integer position);
    }
}
