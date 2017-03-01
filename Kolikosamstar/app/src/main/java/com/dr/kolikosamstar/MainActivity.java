package com.dr.kolikosamstar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Izracunaj button */
    public void calculateAge(View view) {
        //Hide keyboard
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        EditText editText = (EditText) findViewById(R.id.edittext_year);
        String year = editText.getText().toString();
        TextView answer= (TextView) findViewById(R.id.textViewResult);

        Spinner spinnerDay = (Spinner) findViewById(R.id.spinner_days);
       // String day = spinner.getSelectedItem().(); // Small, Medium, Large


        int spinner_pos = spinnerDay.getSelectedItemPosition();
       // String[] size_values = getResources().getStringArray(R.array.size_values);
       // int size = Integer.valueOf(size_values[spinner_pos]);
        Spinner spinnerMonths = (Spinner) findViewById(R.id.spinner_months);
        int spinner_pos1 = spinnerMonths.getSelectedItemPosition();

        answer.setText("Rodjeni ste " + (spinner_pos+1) + ". " + (spinner_pos1+1) + ". " + year + ".");

    }
}
