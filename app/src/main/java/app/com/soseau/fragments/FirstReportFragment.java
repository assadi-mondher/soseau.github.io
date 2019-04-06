package app.com.soseau.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fastaccess.datetimepicker.DatePickerFragmentDialog;
import com.fastaccess.datetimepicker.DateTimeBuilder;
import com.fastaccess.datetimepicker.callback.DatePickerCallback;
import com.fastaccess.datetimepicker.callback.TimePickerCallback;
import com.heinrichreimersoftware.materialintro.app.SlideFragment;

import java.util.Calendar;

import Utils.DrawableClickListener;
import Utils.Util;
import app.com.soseau.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FirstReportFragment extends SlideFragment  implements DatePickerCallback, TimePickerCallback{

    private static FirstReportFragment mInstance;


    @BindView(R.id.mTextViewTitle)
    TextView mTextViewTitle;

    @BindView(R.id.mTextViewDescription)
    TextView mTextViewDescription;

    @BindView(R.id.mRadioGroupTypePb)
    RadioGroup mRadioGroupTypePb;
    private String mDate;
    private String mHour;

    @BindView(R.id.mTextViewDate)
    TextView mTextViewDate;



    public static FirstReportFragment newInstance() {
        return new FirstReportFragment();
    }

    public static FirstReportFragment get()
    {
       return mInstance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstance = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.report_form_1, container, false);
        ButterKnife.bind(this, root);

        Calendar calendar = Calendar.getInstance();
        mDate = Util.getDateInverseFormat(calendar.getTimeInMillis());

        mHour = Util.getTimeOnly(calendar.getTimeInMillis());


        mTextViewDate.setText("Aujourd'hui à" + " " + mHour);
        mTextViewDate.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(mTextViewDate) {
            @Override
            public boolean onDrawableClick() {
                setOnclcikDate();
                return true;
            }
        });

        mTextViewDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    setOnclcikDate();
                    mTextViewDate.clearFocus();
                }
            }
        });

        /*
        if (BuildConfig.DEBUG) {
            // do something for a debug build
            mTextViewTitle.setText("test android");
            mTextViewDescription.setText("description android");
            mTextViewDate.setText("01/01/2017");

            RadioButton btn = (RadioButton) mRadioGroupCategory.getChildAt(1);
            btn.setChecked(true);
        }
        */

        return root;
    }

    @Override
    public boolean canGoForward() {


            if(mTextViewTitle != null && !Util.getContent(mTextViewTitle).isEmpty() &&
               mTextViewDescription != null && !Util.getContent(mTextViewDescription).isEmpty() &&
                    mRadioGroupTypePb != null && (mRadioGroupTypePb.getCheckedRadioButtonId() != -1))
            {

                return true;
            }

            else
            {
                return false;
            }


    }

    void setOnclcikDate() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        DatePickerFragmentDialog.newInstance(
                DateTimeBuilder.get()
                        .withTime(true)
                        .with24Hours(false)
                        .withSelectedDate(currentDate.getTimeInMillis())
                        .withCurrentHour(currentDate.get(Calendar.HOUR_OF_DAY))
                        .withCurrentMinute(currentDate.get(Calendar.MINUTE))
                        .withTheme(R.style.PickersTheme))
                .show(getChildFragmentManager(), "Changer Date");
    }

    @Override
    public void onDateSet(long date) {}

    @Override
    public void onTimeSet(long timeOnly, long dateWithTime) {
        mDate = Util.getDateInverseFormat(dateWithTime);
        mHour = Util.getTimeOnly(dateWithTime);

        mTextViewDate.setText(Util.getDateAndTime(dateWithTime));
    }

    public TextView getmTextViewTitle() {
        return mTextViewTitle;
    }

    public TextView getmTextViewDescription() {
        return mTextViewDescription;
    }


    public RadioGroup getmRadioGroupTypePb() {
        return mRadioGroupTypePb;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmHour() {
        return mHour;
    }
}
