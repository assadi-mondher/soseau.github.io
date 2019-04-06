// Generated code from Butter Knife. Do not modify!
package app.com.soseau.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioGroup;
import app.com.soseau.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SecondPrimeReportFragment_ViewBinding implements Unbinder {
  private SecondPrimeReportFragment target;

  @UiThread
  public SecondPrimeReportFragment_ViewBinding(SecondPrimeReportFragment target, View source) {
    this.target = target;

    target.mRadioGroupUrgence = Utils.findRequiredViewAsType(source, R.id.mRadioGroupUrgence, "field 'mRadioGroupUrgence'", RadioGroup.class);
    target.mRadioGroupNatureProbleme = Utils.findRequiredViewAsType(source, R.id.mRadioGroupNatureProbleme, "field 'mRadioGroupNatureProbleme'", RadioGroup.class);
    target.mRadioGroupEtat = Utils.findRequiredViewAsType(source, R.id.mRadioGroupEtat, "field 'mRadioGroupEtat'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SecondPrimeReportFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRadioGroupUrgence = null;
    target.mRadioGroupNatureProbleme = null;
    target.mRadioGroupEtat = null;
  }
}
