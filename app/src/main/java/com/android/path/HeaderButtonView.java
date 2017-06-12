package com.android.path;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by ritesh on 25/03/17.
 */

public class HeaderButtonView extends FrameLayout {

    public HeaderButtonView(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.header_button_view, this);
    }

    public void displayItem(String text) {
        ((TextView)findViewById(R.id.buttonTextView)).setText(text);
    }
}
