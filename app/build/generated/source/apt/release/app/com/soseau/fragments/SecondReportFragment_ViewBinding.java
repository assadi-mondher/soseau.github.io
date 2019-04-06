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

public class SecondReportFragment_ViewBinding implements Unbinder {
  private SecondReportFragment target;

  @UiThread
  public SecondReportFragment_ViewBinding(SecondReportFragment target, View source) {
    this.target = target;

    target.mRadioGroupImpacte = Utils.findRequiredViewAsType(source, R.id.mRadioGroupImpacte, "field 'mRadioGroupImpacte'", RadioGroup.class);
    target.mRadioGrouptypeEau = Utils.findRequiredViewAsType(source, R.id.mRadioGrouptypeEau, "field 'mRadioGrouptypeEau'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SecondReportFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRadioGroupImpacte = null;
    target.mRadioGrouptypeEau = null;
  }
}
