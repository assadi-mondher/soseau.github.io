package app.com.soseau.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.heinrichreimersoftware.materialintro.app.SlideFragment;

import app.com.soseau.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jazz on 12/03/2017.
 */

public class SecondPrimeReportFragment extends SlideFragment {

    public  static SecondPrimeReportFragment newInstance() {
        return new SecondPrimeReportFragment();
    }

    private static SecondPrimeReportFragment mInstance;

    @BindView(R.id.mRadioGroupUrgence)
    RadioGroup mRadioGroupUrgence;

    @BindView(R.id.mRadioGroupNatureProbleme)
    RadioGroup mRadioGroupNatureProbleme;

    @BindView(R.id.mRadioGroupEtat)
    RadioGroup mRadioGroupEtat;

    public static SecondPrimeReportFragment get()
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

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.report_form_22, container, false);
        ButterKnife.bind(this, root);




        /*
        if(BuildConfig.DEBUG)
        {
            mTextViewTypePlantation.setText("TypePlantation");
            mTextViewNbreArbre.setText("2");
            mTextViewSurface.setText("345");
            mTextViewBesionEau.setText("besoin d'eau");
            mTextViewBesionSemences.setText("besoin de semences");
            mTextViewDelaisEntretien.setText("3");

            RadioButton btn = (RadioButton) mRadioGroupImpacte.getChildAt(1);
            btn.setChecked(true);
        }
        */


        return root;
    }

    @Override
    public boolean canGoForward() {
        if(mRadioGroupUrgence != null && (mRadioGroupUrgence.getCheckedRadioButtonId() != -1) &&
                mRadioGroupNatureProbleme != null && (mRadioGroupNatureProbleme.getCheckedRadioButtonId() != -1) &&
                mRadioGroupEtat != null && (mRadioGroupEtat.getCheckedRadioButtonId() != -1))

                {
                    return true;
                }

                else
                {
                    return false;
                }
    }


    public RadioGroup getmRadioGroupUrgence() {
        return mRadioGroupUrgence;
    }

    public RadioGroup getmRadioGroupNatureProbleme() {
        return mRadioGroupNatureProbleme;
    }

    public RadioGroup getmRadioGroupEtat() {
        return mRadioGroupEtat;
    }
}
