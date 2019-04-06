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
 * Created by jazz on 05/01/2017.
 */
public class SecondReportFragment extends SlideFragment {

    public  static SecondReportFragment newInstance() {
        return new SecondReportFragment();
    }

    private static SecondReportFragment mInstance;

    /*
    @Bind(R.id.mTextViewDatePlantation)
    TextView mTextViewDatePlantation;

    @Bind(R.id.mTextViewTypePlantation)
    TextView mTextViewTypePlantation;

    @Bind(R.id.mTextViewNbreArbre)
    TextView mTextViewNbreArbre;

    @Bind(R.id.mTextViewSurface)
    TextView mTextViewSurface;

    @Bind(R.id.mTextViewBesionEau)
    TextView mTextViewBesionEau;

    @Bind(R.id.mTextViewBesionSemences)
    TextView mTextViewBesionSemences;

    @Bind(R.id.mTextViewDelaisEntretien)
    TextView mTextViewDelaisEntretien;
  */


    @BindView(R.id.mRadioGroupImpacte)
    RadioGroup mRadioGroupImpacte;

    @BindView(R.id.mRadioGrouptypeEau)
    RadioGroup mRadioGrouptypeEau;


    public static SecondReportFragment get()
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
        View root = inflater.inflate(R.layout.report_form_2, container, false);
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


        if(mRadioGrouptypeEau != null && (mRadioGrouptypeEau.getCheckedRadioButtonId() != -1) &&
           mRadioGroupImpacte != null && (mRadioGroupImpacte.getCheckedRadioButtonId() != -1))

                {
                    return true;
                }

                else
                {
                    return false;
                }
    }


    public RadioGroup getmRadioGroupImpacte() {
        return mRadioGroupImpacte;
    }

    public RadioGroup getmRadioGrouptypeEau() {
        return mRadioGrouptypeEau;
    }
}
