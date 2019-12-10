package com.example.calendarcherepanov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button mStartDateBtn;
    private Button mEndDateBtn;
    private Button mOkBtn;
    private CalendarView mStartCalendar;
    private CalendarView mEndCalendar;
    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mStartDateBtn = findViewById(R.id.chooseStartDate);
        mEndDateBtn = findViewById(R.id.chooseEndDate);
        mOkBtn = findViewById(R.id.btnOK);
        mStartCalendar = findViewById(R.id.startDateCalendar);
        mEndCalendar = findViewById(R.id.endDateCalendar);
        startCalendarShow();
        endCalendarShow();
        startCalendarInit();
        endCalendarInit();
        okBtnAct();
    }

    private void startCalendarInit() {
        mStartCalendar.setVisibility(View.GONE);
        mStartCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                mStartDateTxt = i2+"."+(i1 + 1)+"."+i;
                mStartDateBtn.setText("Дата-время старта задачи: " + mStartDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, (i1 + 1), i2);
                mStartDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });
    }

    private void endCalendarInit() {
        mEndCalendar.setVisibility(View.GONE);
        mEndCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                mEndDateTxt = i2+"."+(i1 + 1)+"."+i;
                mEndDateBtn.setText("Дата-время окончания задачи: " + mEndDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, (i1+1), i2);
                mEndDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });
    }

    private void startCalendarShow() {
        mStartDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStartCalendar.getVisibility() == View.GONE) {
                    mStartCalendar.setVisibility(View.VISIBLE);
                    mEndCalendar.setVisibility(View.GONE);
                }
                else {
                    mStartCalendar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void endCalendarShow() {
        mEndDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEndCalendar.getVisibility() == View.GONE) {
                    mEndCalendar.setVisibility(View.VISIBLE);
                    mStartCalendar.setVisibility(View.GONE);
                }
                else {
                    mEndCalendar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void okBtnAct() {
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEndDate >= mStartDate) {
                    Toast.makeText(MainActivity.this
                            , mStartDateTxt
                                    + " - "
                                    + mEndDateTxt
                            , Toast.LENGTH_LONG)
                            .show();;
                }

                else {
                    Toast.makeText(MainActivity.this
                            , getString(R.string.error)
                            , Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}
