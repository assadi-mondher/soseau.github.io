// Generated code from Butter Knife. Do not modify!
package app.com.soseau.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import app.com.soseau.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FirstReportFragment_ViewBinding implements Unbinder {
  private FirstReportFragment target;

  @UiThread
  public FirstReportFragment_ViewBinding(FirstReportFragment target, View source) {
    this.target = target;

    target.mTextViewTitle = Utils.findRequiredViewAsType(source, R.id.mTextViewTitle, "field 'mTextViewTitle'", TextView.class);
    target.mTextViewDescription = Utils.findRequiredViewAsType(source, R.id.mTextViewDescription, "field 'mTextViewDescription'", TextView.class);
    target.mRadioGroupTypePb = Utils.findRequiredViewAsType(source, R.id.mRadioGroupTypePb, "field 'mRadioGroupTypePb'", RadioGroup.class);
    target.mTextViewDate = Utils.findRequiredViewAsType(source, R.id.mTextViewDate, "field 'mTextViewDate'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FirstReportFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTextViewTitle = null;
    target.mTextViewDescription = null;
    target.mRadioGroupTypePb = null;
    target.mTextViewDate = null;
  }
}
