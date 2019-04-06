// Generated code from Butter Knife. Do not modify!
package app.com.soseau.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import app.com.soseau.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FourthReportFragment_ViewBinding implements Unbinder {
  private FourthReportFragment target;

  private View view2131296399;

  @UiThread
  public FourthReportFragment_ViewBinding(final FourthReportFragment target, View source) {
    this.target = target;

    View view;
    target.image_gridView = Utils.findRequiredViewAsType(source, R.id.gridview, "field 'image_gridView'", GridView.class);
    target.mTextViewPrenom = Utils.findRequiredViewAsType(source, R.id.mTextViewPrenom, "field 'mTextViewPrenom'", EditText.class);
    target.mTextViewNomFamille = Utils.findRequiredViewAsType(source, R.id.mTextViewNomFamille, "field 'mTextViewNomFamille'", EditText.class);
    target.mTextViewEmail = Utils.findRequiredViewAsType(source, R.id.mTextViewEmail, "field 'mTextViewEmail'", EditText.class);
    view = Utils.findRequiredView(source, R.id.mImageButtonAddImage, "method 'addPhoto'");
    view2131296399 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addPhoto();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    FourthReportFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image_gridView = null;
    target.mTextViewPrenom = null;
    target.mTextViewNomFamille = null;
    target.mTextViewEmail = null;

    view2131296399.setOnClickListener(null);
    view2131296399 = null;
  }
}
