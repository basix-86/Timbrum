package it.buch85.timbrum.prefs;

import it.buch85.timbrum.MainActivity;

import java.util.Calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

public class TimePreference extends DialogPreference {
    private Calendar calendar;
    private TimePicker picker = null;

    public TimePreference(Context ctxt) {
        super(ctxt, null);
    }

    public TimePreference(Context ctxt, AttributeSet attrs) {
        this(ctxt, attrs,  android.R.attr.editTextPreferenceStyle);
    }

    public TimePreference(Context ctxt, AttributeSet attrs, int defStyle) {
        super(ctxt, attrs, defStyle);

        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);
        calendar =Calendar.getInstance();
    }

    @Override
    protected View onCreateDialogView() {
        picker = new TimePicker(getContext());
        picker.setIs24HourView(true);
        return (picker);
    }
    
    @Override
    protected View onCreateView(ViewGroup parent) {
    	return super.onCreateView(parent);
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);
        picker.setCurrentHour(calendar.get(Calendar.HOUR)-1);
        picker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
        	int hour=picker.getCurrentHour();
        	int minute=picker.getCurrentMinute() + (hour*60);
        	int seconds = minute*60;
        	long millis=seconds*1000;
        	calendar.setTimeInMillis(millis);
            setSummary(getSummary());
            if (callChangeListener(millis)) {
                persistLong(millis);
                notifyChanged();
            }
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return (a.getString(index));
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {

        if (restoreValue) {
            if (defaultValue == null) {
                calendar.setTimeInMillis(getPersistedLong(System.currentTimeMillis()));
            } else {
                calendar.setTimeInMillis(Long.parseLong(getPersistedString((String) defaultValue)));
            }
        } else {
            if (defaultValue == null) {
                calendar.setTimeInMillis(System.currentTimeMillis());
            } else {
                calendar.setTimeInMillis(Long.parseLong((String) defaultValue));
            }
        }
        setSummary(getSummary());
    }

    @Override
    public CharSequence getSummary() {
        if (calendar == null) {
            return null;
        }
        return MainActivity.formatTime(calendar.getTimeInMillis());
    }
} 
