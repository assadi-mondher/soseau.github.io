// Generated code from Butter Knife. Do not modify!
package app.com.soseau.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.com.soseau.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailIncidentActivity_ViewBinding implements Unbinder {
  private DetailIncidentActivity target;

  private View view2131296551;

  private View view2131296515;

  @UiThread
  public DetailIncidentActivity_ViewBinding(DetailIncidentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailIncidentActivity_ViewBinding(final DetailIncidentActivity target, View source) {
    this.target = target;

    View view;
    target.mImageViewPhoto = Utils.findRequiredViewAsType(source, R.id.mImageViewPhoto, "field 'mImageViewPhoto'", ImageView.class);
    target.mTextViewTitle = Utils.findRequiredViewAsType(source, R.id.mTextViewTitle, "field 'mTextViewTitle'", TextView.class);
    target.mTextViewDate = Utils.findRequiredViewAsType(source, R.id.mTextViewDate, "field 'mTextViewDate'", TextView.class);
    target.mTextViewLocation = Utils.findRequiredViewAsType(source, R.id.mTextViewLocation, "field 'mTextViewLocation'", TextView.class);
    target.mTextViewDescription = Utils.findRequiredViewAsType(source, R.id.mTextViewDescription, "field 'mTextViewDescription'", TextView.class);
    target.customFields_layout = Utils.findRequiredViewAsType(source, R.id.customFields_layout, "field 'customFields_layout'", LinearLayout.class);
    target.photoGallery_layout = Utils.findRequiredViewAsType(source, R.id.photoGallery_layout, "field 'photoGallery_layout'", LinearLayout.class);
    target.photoGallery_view = Utils.findRequiredViewAsType(source, R.id.photoGallery_view, "field 'photoGallery_view'", CardView.class);
    view = Utils.findRequiredView(source, R.id.video_view, "field 'video_view' and method 'setOnClickVideo'");
    target.video_view = Utils.castView(view, R.id.video_view, "field 'video_view'", CardView.class);
    view2131296551 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setOnClickVideo();
      }
    });
    view = Utils.findRequiredView(source, R.id.share, "method 'setOnClickShare'");
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
    DetailIncidentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mImageViewPhoto = null;
    target.mTextViewTitle = null;
    target.mTextViewDate = null;
    target.mTextViewLocation = null;
    target.mTextViewDescription = null;
    target.customFields_layout = null;
    target.photoGallery_layout = null;
    target.photoGallery_view = null;
    target.video_view = null;

    view2131296551.setOnClickListener(null);
    view2131296551 = null;
    view2131296515.setOnClickListener(null);
    view2131296515 = null;
  }
}
