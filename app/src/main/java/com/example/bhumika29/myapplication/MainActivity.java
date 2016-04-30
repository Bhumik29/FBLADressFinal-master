package com.example.bhumika29.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import com.buddy.sdk.*;
import com.buddy.sdk.models.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v)
    {
        if(v.getId()== R.id.button);
        {
            Intent i= new Intent(MainActivity.this, Main2Activity.class);
            startActivity(i);
        }
    }
}
