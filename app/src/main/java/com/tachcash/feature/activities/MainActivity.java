package com.tachcash.feature.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.tachcash.R;
import com.tachcash.base.BaseActivity;
import com.tachcash.feature.fragments.CatalogFragment;
import com.tachcash.feature.fragments.MapFragment;
import com.tachcash.feature.fragments.PaymentFragment;
import com.tachcash.feature.presenters.MainPresenter;
import com.tachcash.feature.views.MainView;

import static com.tachcash.utils.Constants.FRAGMENT_CATALOG;
import static com.tachcash.utils.Constants.FRAGMENT_MAP;
import static com.tachcash.utils.Constants.FRAGMENT_PAYMENT;
import static com.tachcash.utils.ViewUtil.removeShiftMode;

public class MainActivity extends BaseActivity implements MainView {

  @InjectPresenter MainPresenter mMainPresenter;

  @BindView(R.id.bottomBar) BottomNavigationView mBottomNavigationView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);

    mNavigator.addFragmentTag(this, R.id.container_main, CatalogFragment.newInstance(),
        FRAGMENT_CATALOG);

    removeShiftMode(mBottomNavigationView);
    mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
      switch (item.getItemId()) {
        case R.id.tab_catalog:
          mNavigator.replaceFragmentTagNotCopy(this, R.id.container_main,
              CatalogFragment.newInstance(), FRAGMENT_CATALOG);
          break;
        case R.id.tab_payment:
          mNavigator.replaceFragmentTagNotCopy(this, R.id.container_main,
              PaymentFragment.newInstance(), FRAGMENT_PAYMENT);
          break;
        case R.id.tab_map:
          mNavigator.replaceFragmentTagNotCopy(this, R.id.container_main, MapFragment.newInstance(),
              FRAGMENT_MAP);
      }
      return true;
    });
  }
}
