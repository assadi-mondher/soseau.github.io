// Generated code from Butter Knife. Do not modify!
package app.com.soseau.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import app.com.soseau.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import views.ClearableEditText;

public class AuthentificationActivity_ViewBinding implements Unbinder {
  private AuthentificationActivity target;

  private View view2131296283;

  @UiThread
  public AuthentificationActivity_ViewBinding(AuthentificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AuthentificationActivity_ViewBinding(final AuthentificationActivity target, View source) {
    this.target = target;

    View view;
    target.email = Utils.findRequiredViewAsType(source, R.id.email, "field 'email'", ClearableEditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", ClearableEditText.class);
    view = Utils.findRequiredView(source, R.id.authentification_btn, "method 'setAuthentificationBtn'");
    view2131296283 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setAuthentificationBtn();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AuthentificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.email = null;
    target.password = null;

    view2131296283.setOnClickListener(null);
    view2131296283 = null;
  }
}
