package com.android.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class AppLanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_language);
        ListView view = (ListView)findViewById(R.id.lang_list);
        //TODO set app locale
    }

    public void gotoLocation(View view) {
        Log.d("AppLanguageActivity", "Starting LocationActivity");
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }
}
