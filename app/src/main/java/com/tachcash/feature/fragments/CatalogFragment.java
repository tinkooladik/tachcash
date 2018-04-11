package com.tachcash.feature.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.tachcash.R;
import com.tachcash.base.BaseFragment;
import com.tachcash.feature.presenters.CatalogPresenter;
import com.tachcash.feature.views.CatalogView;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class CatalogFragment extends BaseFragment implements CatalogView {

  @InjectPresenter CatalogPresenter mCatalogPresenter;

  public CatalogFragment() {
    super(R.layout.fragment_catalog);
  }

  public static CatalogFragment newInstance() {
    Bundle args = new Bundle();
    CatalogFragment fragment = new CatalogFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}
