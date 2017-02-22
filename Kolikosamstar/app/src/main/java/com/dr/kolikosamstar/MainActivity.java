package com.dr.kolikosamstar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Izracunaj button */
    public void calculateAge(View view) {
        EditText editText = (EditText) findViewById(R.id.edittext_year);
        String year = editText.getText().toString();
    }
}
