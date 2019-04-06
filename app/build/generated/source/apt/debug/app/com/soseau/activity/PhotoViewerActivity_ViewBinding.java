// Generated code from Butter Knife. Do not modify!
package app.com.soseau.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import app.com.soseau.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import views.HackyViewPager;

public class PhotoViewerActivity_ViewBinding implements Unbinder {
  private PhotoViewerActivity target;

  private View view2131296515;

  @UiThread
  public PhotoViewerActivity_ViewBinding(PhotoViewerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhotoViewerActivity_ViewBinding(final PhotoViewerActivity target, View source) {
    this.target = target;

    View view;
    target.view_pager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'view_pager'", HackyViewPager.class);
    view = Utils.findRequiredView(source, R.id.share, "field 'share' and method 'setOnClickShare'");
    target.share = Utils.castView(view, R.id.share, "field 'share'", FloatingActionButton.class);
    view2131296515 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setOnClickShare();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PhotoViewerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.view_pager = null;
    target.share = null;

    view2131296515.setOnClickListener(null);
    view2131296515 = null;
  }
}
