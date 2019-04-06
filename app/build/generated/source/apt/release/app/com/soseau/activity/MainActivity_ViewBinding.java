// Generated code from Butter Knife. Do not modify!
package app.com.soseau.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import app.com.soseau.R;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.nguyenhoanglam.progresslayout.ProgressLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131296376;

  private View view2131296377;

  private View view2131296378;

  private View view2131296379;

  private View view2131296380;

  private View view2131296381;

  private View view2131296382;

  private View view2131296383;

  private View view2131296384;

  private View view2131296385;

  private View view2131296375;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.progressLayout = Utils.findRequiredViewAsType(source, R.id.progressLayout, "field 'progressLayout'", ProgressLayout.class);
    view = Utils.findRequiredView(source, R.id.mActionButtonAll, "field 'mActionButtonAll' and method 'setmActionButtonSurpaturage'");
    target.mActionButtonAll = Utils.castView(view, R.id.mActionButtonAll, "field 'mActionButtonAll'", FloatingActionButton.class);
    view2131296376 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmActionButtonSurpaturage();
      }
    });
    view = Utils.findRequiredView(source, R.id.mActionButtonAssainissement, "field 'mActionButtonAssainissement' and method 'setmActionButtonAssainissement'");
    target.mActionButtonAssainissement = Utils.castView(view, R.id.mActionButtonAssainissement, "field 'mActionButtonAssainissement'", FloatingActionButton.class);
    view2131296377 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmActionButtonAssainissement();
      }
    });
    view = Utils.findRequiredView(source, R.id.mActionButtonCoupure, "field 'mActionButtonCoupure' and method 'setmActionButtonCoupure'");
    target.mActionButtonCoupure = Utils.castView(view, R.id.mActionButtonCoupure, "field 'mActionButtonCoupure'", FloatingActionButton.class);
    view2131296378 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmActionButtonCoupure();
      }
    });
    view = Utils.findRequiredView(source, R.id.mActionButtonDistributionNonEquitable, "field 'mActionButtonDistributionNonEquitable' and method 'setmActionButtonDistributionNonEquitable'");
    target.mActionButtonDistributionNonEquitable = Utils.castView(view, R.id.mActionButtonDistributionNonEquitable, "field 'mActionButtonDistributionNonEquitable'", FloatingActionButton.class);
    view2131296379 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmActionButtonDistributionNonEquitable();
      }
    });
    view = Utils.findRequiredView(source, R.id.mActionButtonFuiteEau, "field 'mActionButtonFuiteEau' and method 'setmActionButtonFuiteEau'");
    target.mActionButtonFuiteEau = Utils.castView(view, R.id.mActionButtonFuiteEau, "field 'mActionButtonFuiteEau'", FloatingActionButton.class);
    view2131296380 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmActionButtonFuiteEau();
      }
    });
    view = Utils.findRequiredView(source, R.id.mActionButtonMouvementDeProtestation, "field 'mActionButtonMouvementDeProtestation' and method 'setmActionButtonMouvementDeProtestation'");
    target.mActionButtonMouvementDeProtestation = Utils.castView(view, R.id.mActionButtonMouvementDeProtestation, "field 'mActionButtonMouvementDeProtestation'", FloatingActionButton.class);
    view2131296381 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmActionButtonMouvementDeProtestation();
      }
    });
    view = Utils.findRequiredView(source, R.id.mActionButtonPasAccesHabitant, "field 'mActionButtonPasAccesHabitant' and method 'setmActionButtonPasAccesHabitant'");
    target.mActionButtonPasAccesHabitant = Utils.castView(view, R.id.mActionButtonPasAccesHabitant, "field 'mActionButtonPasAccesHabitant'", FloatingActionButton.class);
    view2131296382 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmActionButtonPasAccesHabitant();
      }
    });
    view = Utils.findRequiredView(source, R.id.mActionButtonPollution, "field 'mActionButtonPollution' and method 'setmActionButtonPollution'");
    target.mActionButtonPollution = Utils.castView(view, R.id.mActionButtonPollution, "field 'mActionButtonPollution'", FloatingActionButton.class);
    view2131296383 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmActionButtonPollution();
      }
    });
    view = Utils.findRequiredView(source, R.id.mActionButtonPotabilite, "field 'mActionButtonPotabilite' and method 'setmActionButtonPotabilite'");
    target.mActionButtonPotabilite = Utils.castView(view, R.id.mActionButtonPotabilite, "field 'mActionButtonPotabilite'", FloatingActionButton.class);
    view2131296384 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmActionButtonPotabilite();
      }
    });
    view = Utils.findRequiredView(source, R.id.mActionButtonSurexploiatation, "field 'mActionButtonSurexploiatation' and method 'setmActionButtonSurexploiatation'");
    target.mActionButtonSurexploiatation = Utils.castView(view, R.id.mActionButtonSurexploiatation, "field 'mActionButtonSurexploiatation'", FloatingActionButton.class);
    view2131296385 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmActionButtonSurexploiatation();
      }
    });
    view = Utils.findRequiredView(source, R.id.mActionButtCorruption, "field 'mActionButtCorruption' and method 'setmActionButtCorruption'");
    target.mActionButtCorruption = Utils.castView(view, R.id.mActionButtCorruption, "field 'mActionButtCorruption'", FloatingActionButton.class);
    view2131296375 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setmActionButtCorruption();
      }
    });
    target.menu = Utils.findRequiredViewAsType(source, R.id.menu, "field 'menu'", FloatingActionMenu.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progressLayout = null;
    target.mActionButtonAll = null;
    target.mActionButtonAssainissement = null;
    target.mActionButtonCoupure = null;
    target.mActionButtonDistributionNonEquitable = null;
    target.mActionButtonFuiteEau = null;
    target.mActionButtonMouvementDeProtestation = null;
    target.mActionButtonPasAccesHabitant = null;
    target.mActionButtonPollution = null;
    target.mActionButtonPotabilite = null;
    target.mActionButtonSurexploiatation = null;
    target.mActionButtCorruption = null;
    target.menu = null;

    view2131296376.setOnClickListener(null);
    view2131296376 = null;
    view2131296377.setOnClickListener(null);
    view2131296377 = null;
    view2131296378.setOnClickListener(null);
    view2131296378 = null;
    view2131296379.setOnClickListener(null);
    view2131296379 = null;
    view2131296380.setOnClickListener(null);
    view2131296380 = null;
    view2131296381.setOnClickListener(null);
    view2131296381 = null;
    view2131296382.setOnClickListener(null);
    view2131296382 = null;
    view2131296383.setOnClickListener(null);
    view2131296383 = null;
    view2131296384.setOnClickListener(null);
    view2131296384 = null;
    view2131296385.setOnClickListener(null);
    view2131296385 = null;
    view2131296375.setOnClickListener(null);
    view2131296375 = null;
  }
}
