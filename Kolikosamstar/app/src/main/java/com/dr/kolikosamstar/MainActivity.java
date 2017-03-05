package com.dr.kolikosamstar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class MainActivity extends AppCompatActivity {

    private static float f = 0;
    private static long g = 0;

    final Runnable r = new Runnable() {
        public void run() {
            TextView myTextView = (TextView) findViewById(R.id.secondsText);

            f = (float) (f - 0.1);

            if (f < 0) {
                f = (float) 59.9;
                TextView myTextView1 = (TextView) findViewById(R.id.minutesText);
                g--;
                myTextView1.setText("Minuta:\n"+  g);

            }

            myTextView.setText(String.format( "Sekundi:\n%.1f",  f ));
            myTextView.postDelayed(this, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // TextView myTextView = (TextView) findViewById(R.id.secondsText);
       // myTextView.postDelayed(r, 1000);
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
        Integer yearInt = Integer.parseInt(year);
        TextView answer= (TextView) findViewById(R.id.answerText);
        TextView textLeft= (TextView) findViewById(R.id.textLeft);
        TextView answerYears= (TextView) findViewById(R.id.yearsText);
        TextView answerMonths= (TextView) findViewById(R.id.monthsText);
        TextView answerDays= (TextView) findViewById(R.id.daysText);
        TextView answerHours= (TextView) findViewById(R.id.hoursText);
        TextView answerMinutes= (TextView) findViewById(R.id.minutesText);
        TextView answerSeconds= (TextView) findViewById(R.id.secondsText);

        Spinner spinnerDay = (Spinner) findViewById(R.id.spinner_days);
       // String day = spinner.getSelectedItem().(); // Small, Medium, Large


        int spinner_pos = spinnerDay.getSelectedItemPosition();
       // String[] size_values = getResources().getStringArray(R.array.size_values);
       // int size = Integer.valueOf(size_values[spinner_pos]);
        Spinner spinnerMonths = (Spinner) findViewById(R.id.spinner_months);
        int spinner_pos1 = spinnerMonths.getSelectedItemPosition();

        //answer.setText("Rodjeni ste " + (spinner_pos+1) + ". " + (spinner_pos1+1) + ". " + year + ".");

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();
        calendar1.set(yearInt, spinner_pos1,spinner_pos+1, 0, 0, 0);
        calendar2.getTime();
        long milsecs1= calendar1.getTimeInMillis();
        long milsecs2 = calendar2.getTimeInMillis();
        long diff = milsecs2 - milsecs1;
        long dsecs = diff / 1000;
        long dminutes = diff / (60 * 1000);
        long dhours = diff / (60 * 60 * 1000);
        long ddays = diff / (24 * 60 * 60 * 1000);
        long dmonths = diff / (30L * 24 * 60 * 60 * 1000);

        Date date1 = calendar1.getTime();
        Date date2 = calendar2.getTime();
        long diff1 = date1.getTime() - date2.getTime(); //this is going to give you the difference in milliseconds

        Date result = new Date(diff1);
        Format frmt = new SimpleDateFormat("yy MM dd HH:mm:ss");

        long star[] = differenceBetweenDates(date1, date2);
        SimpleDateFormat ft =
                new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        SimpleDateFormat ft1 =
                new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        int left = (int)star[2] % 10;

        left = 10 - left;

        calendar3.set(yearInt + left + (int)star[2], spinner_pos1,spinner_pos+1, 0, 0, 0);

        long xmilsecs1= calendar2.getTimeInMillis();
        long xmilsecs2 = calendar3.getTimeInMillis();
        long xdiff = xmilsecs2 - xmilsecs1;
        long xdsecs = xdiff / 1000%60;
        long xdminutes = xdiff / (60 * 1000)%60;
        long xdhours = xdiff / (60 * 60 * 1000)%24;
        long xddays = xdiff / (24 * 60 * 60 * 1000)%30;
        long xdmonths = xdiff / (30L * 24 * 60 * 60 * 1000)%12;
        long xdyears = xdiff / (12L * 30L * 24 * 60 * 60 * 1000);



        answer.setText(/*ft.format(date1) +ft1.format(date2)+ */"Star si " + star[2] + " godina, " +  star[1] + " mjeseci i " + star[0] + " dana.");
        textLeft.setText("Do tvog " + (left + (int)star[2]) + ". rodjendana ti je ostalo jos:");

        answerYears.setText("Godina:\n" + xdyears);
        answerMonths.setText("Mjeseci:\n" + xdmonths);
        answerDays.setText("Dana:\n" +xddays);
        answerHours.setText("Sati:\n" +xdhours);
        answerMinutes.setText("Minuta:\n" +xdminutes);
        answerSeconds.setText("Sekundi:\n" + xdsecs);

        f = xdsecs;
        g = xdminutes;
        TextView myTextView = (TextView) findViewById(R.id.secondsText);
        myTextView.removeCallbacks(r);
        myTextView.postDelayed(r, 100);
    }

    public static long[] differenceBetweenDates(Date fromDate, Date toDate) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(fromDate);
        long years = 0;
        long months = 0;
        long days = 0;
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(toDate);
        Calendar tmpdate = Calendar.getInstance();
        tmpdate.setTime(startDate.getTime());

        tmpdate.add(Calendar.YEAR, 1);
        while (tmpdate.compareTo(endDate) <= 0) {
            startDate.add(Calendar.YEAR, 1);
            tmpdate.add(Calendar.YEAR, 1);
            years++;
        }
        tmpdate.setTime(startDate.getTime());
        tmpdate.add(Calendar.MONTH, 1);
        while (tmpdate.compareTo(endDate) <= 0) {
            startDate.add(Calendar.MONTH, 1);
            tmpdate.add(Calendar.MONTH, 1);
            months++;
        }
        tmpdate.setTime(startDate.getTime());
        tmpdate.add(Calendar.DATE, 1);
        while (tmpdate.compareTo(endDate) <= 0) {
            startDate.add(Calendar.DATE, 1);
            tmpdate.add(Calendar.DATE, 1);
            days++;
        }

        return new long[]{days, months, years};
    }
}
