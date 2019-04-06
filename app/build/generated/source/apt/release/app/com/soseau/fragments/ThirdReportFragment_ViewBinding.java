// Generated code from Butter Knife. Do not modify!
package app.com.soseau.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import app.com.soseau.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ThirdReportFragment_ViewBinding implements Unbinder {
  private ThirdReportFragment target;

  private View view2131296400;

  @UiThread
  public ThirdReportFragment_ViewBinding(final ThirdReportFragment target, View source) {
    this.target = target;

    View view;
    target.mTextViewLocalite = Utils.findRequiredViewAsType(source, R.id.mTextViewLocalite, "field 'mTextViewLocalite'", EditText.class);
    target.mTextViewPresseLink = Utils.findRequiredViewAsType(source, R.id.mTextViewPresseLink, "field 'mTextViewPresseLink'", EditText.class);
    target.mTextViewVideoLink = Utils.findRequiredViewAsType(source, R.id.mTextViewVideoLink, "field 'mTextViewVideoLink'", EditText.class);
    view = Utils.findRequiredView(source, R.id.mImageButtonLocalite, "field 'mImageButtonLocalite' and method 'setmImageButtonLocalite'");
    target.mImageButtonLocalite = Utils.castView(view, R.id.mImageButtonLocalite, "field 'mImageButtonLocalite'", ImageButton.class);
    view2131296400 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmImageButtonLocalite();
      }
    });
    target.info_sup = Utils.findRequiredViewAsType(source, R.id.info_sup, "field 'info_sup'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ThirdReportFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTextViewLocalite = null;
    target.mTextViewPresseLink = null;
    target.mTextViewVideoLink = null;
    target.mImageButtonLocalite = null;
    target.info_sup = null;

    view2131296400.setOnClickListener(null);
    view2131296400 = null;
  }
}
