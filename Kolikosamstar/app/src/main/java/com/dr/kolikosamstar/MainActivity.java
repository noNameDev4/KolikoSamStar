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

    private static float seconds = 0;
    private static long minutes = 0;
    private static long hours = 0;
    private static long days = 0;
    private static long months = 0;
    private static long years = 0;

    final Runnable r = new Runnable() {
        public void run() {

            TextView textSeconds = (TextView) findViewById(R.id.secondsText);
            TextView textRodjendan = (TextView) findViewById(R.id.textRodjendan);
            TextView textMinutes = (TextView) findViewById(R.id.minutesText);
            TextView textHours = (TextView) findViewById(R.id.hoursText);
            TextView textDays = (TextView) findViewById(R.id.daysText);
            TextView textMonths = (TextView) findViewById(R.id.monthsText);
            TextView textYears = (TextView) findViewById(R.id.yearsText);

            seconds = (float) (seconds - 0.1);

            if (seconds < 0) {
                if (minutes == 0 && hours == 0 && days == 0 && months == 0 && years == 0) {
                    textSeconds.setText("");
                    textMinutes.setText("");
                    textHours.setText("");
                    textDays.setText("");
                    textMonths.setText("");
                    textYears.setText("");
                    textRodjendan.setText("Cestitke!!! Upravo ste ušli u novo desetljeće svog života!!!");
                } else {
                    seconds = (float) 59.9;
                    textSeconds.setText(String.format("Sekundi:\n%.1f", seconds));
                    minutes--;
                    if (minutes < 0) {
                        if (hours == 0 && days == 0 && months == 0 && years == 0) {
                            textSeconds.setText("");
                            textMinutes.setText("");
                            textHours.setText("");
                            textDays.setText("");
                            textMonths.setText("");
                            textYears.setText("");
                            textRodjendan.setText("Cestitke!!! Upravo ste ušli u novo desetljeće svog života!!!");
                        } else {
                            minutes = 59;
                            textMinutes.setText("Minuta:\n" + minutes);
                            hours--;
                            if (hours < 0) {
                                if (days == 0 && months == 0 && years == 0) {
                                    textSeconds.setText("");
                                    textMinutes.setText("");
                                    textHours.setText("");
                                    textDays.setText("");
                                    textMonths.setText("");
                                    textYears.setText("");
                                    textRodjendan.setText("Cestitke!!! Upravo ste ušli u novo desetljeće svog života!!!");
                                } else {
                                    hours = 23;
                                    textHours.setText("Sati:\n" + hours);
                                    days--;
                                    if (days < 0) {
                                        if (months == 0 && years == 0) {
                                            textSeconds.setText("");
                                            textMinutes.setText("");
                                            textHours.setText("");
                                            textDays.setText("");
                                            textMonths.setText("");
                                            textYears.setText("");
                                            textRodjendan.setText("Cestitke!!! Upravo ste ušli u novo desetljeće svog života!!!");
                                        } else {
                                            days = 29;
                                            textDays.setText("Dana:\n" + days);
                                            months--;
                                            if (months < 0) {
                                                if (years == 0) {
                                                    textSeconds.setText("");
                                                    textMinutes.setText("");
                                                    textHours.setText("");
                                                    textDays.setText("");
                                                    textMonths.setText("");
                                                    textYears.setText("");
                                                    textRodjendan.setText("Cestitke!!! Upravo ste ušli u novo desetljeće svog života!!!");
                                                } else {
                                                    months = 11;
                                                    textMonths.setText("Mjeseci:\n" + months);
                                                    years--;
                                                    textYears.setText("Godina:\n" + years);
                                                }
                                            } else {
                                                textMonths.setText("Mjeseci:\n" + months);
                                            }
                                        }
                                    } else {
                                        textDays.setText("Dana:\n" + days);
                                    }
                                }
                            } else {
                                textHours.setText("Sati:\n" + hours);
                            }
                        }
                    } else {
                        textMinutes.setText("Minuta:\n" + minutes);
                    }
                }
            } else {
                textSeconds.setText(String.format("Sekundi:\n%.1f", seconds));
            }

            textSeconds.postDelayed(this, 100);
        }
    };

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
        TextView textRodjendan = (TextView) findViewById(R.id.textRodjendan);
        textRodjendan.setText("");

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

        int day = spinnerDay.getSelectedItemPosition() + 1;
        Spinner spinnerMonths = (Spinner) findViewById(R.id.spinner_months);
        int month = spinnerMonths.getSelectedItemPosition();

        Calendar calInput = Calendar.getInstance();
        Calendar calToday = Calendar.getInstance();
        Calendar calFuture = Calendar.getInstance();
        calInput.set(yearInt, month, day, 0, 0, 0);
        calToday.getTime();

//        long milsecs1= calInput.getTimeInMillis();
//        long milsecs2 = calToday.getTimeInMillis();
//        long diff = milsecs2 - milsecs1;
//        long dsecs = diff / 1000;
//        long dminutes = diff / (60 * 1000);
//        long dhours = diff / (60 * 60 * 1000);
//        long ddays = diff / (24 * 60 * 60 * 1000);
//        long dmonths = diff / (30L * 24 * 60 * 60 * 1000);
//        Date dateInput = calInput.getTime();
//        Date dateToday = calToday.getTime();
//        long diff1 = dateInput.getTime() - dateToday.getTime(); //this is going to give you the difference in milliseconds
//        Date result = new Date(diff1);
//        Format frmt = new SimpleDateFormat("yy MM dd HH:mm:ss");
//        SimpleDateFormat ft =
//                new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//        SimpleDateFormat ft1 =
//                new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//

        Date dateInput = calInput.getTime();
        Date dateToday = calToday.getTime();
        long star[] = differenceBetweenDates(dateInput, dateToday);

        int timeLeft = (int)star[2] % 10;
        timeLeft = 10 - timeLeft;
        calFuture.set(yearInt + timeLeft + (int)star[2], month,day, 0, 0, 0);

        long xmilsecs1= calToday.getTimeInMillis();
        long xmilsecs2 = calFuture.getTimeInMillis();
        long xdiff = xmilsecs2 - xmilsecs1;
        long xdsecs = xdiff / 1000%60;
        long xdminutes = xdiff / (60 * 1000)%60;
        long xdhours = xdiff / (60 * 60 * 1000)%24;
        long xddays = xdiff / (24 * 60 * 60 * 1000)%30;
        long xdmonths = xdiff / (30L * 24 * 60 * 60 * 1000)%12;
        long xdyears = xdiff / (12L * 30L * 24 * 60 * 60 * 1000);

        answer.setText("Stari ste " + star[2] + " godina, " +  star[1] + " mjeseci i " + star[0] + " dana.");
        textLeft.setText("Do Vašeg " + (timeLeft + (int)star[2]) + ". rođendana Vam je ostalo još:");

        answerYears.setText("Godina:\n" + xdyears);
        answerMonths.setText("Mjeseci:\n" + xdmonths);
        answerDays.setText("Dana:\n" +xddays);
        answerHours.setText("Sati:\n" +xdhours);
        answerMinutes.setText("Minuta:\n" +xdminutes);
        answerSeconds.setText("Sekundi:\n" + xdsecs);

        seconds = xdsecs;
        minutes = xdminutes;
        hours = xdhours;
        days = xddays;
        months = xdmonths;
        years = xdyears;

        answerSeconds.removeCallbacks(r);
        answerSeconds.postDelayed(r, 100);
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
