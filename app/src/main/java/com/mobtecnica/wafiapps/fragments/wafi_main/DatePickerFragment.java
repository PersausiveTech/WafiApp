package com.mobtecnica.wafiapps.fragments.wafi_main;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */

public class DatePickerFragment extends DialogFragment {
    DatePickerDialog.OnDateSetListener ondateSet;
    private int year, month, day;

    public DatePickerFragment() {}

    public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {
        ondateSet = ondate;
    }

    @SuppressLint("NewApi")
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), ondateSet, year, month, day);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year-120); //120 years max
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        c = Calendar.getInstance();
        c.set(Calendar.YEAR,year-12); //12 years min
        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
//        datePickerDialog.getDatePicker().updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE));
        return datePickerDialog;
    }
}