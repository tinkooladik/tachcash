package com.tachcash.feature.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.tachcash.R;
import com.tachcash.base.BaseFragment;
import com.tachcash.feature.presenters.TemplatesPresenter;
import com.tachcash.feature.views.TemplatesView;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class TemplatesFragment extends BaseFragment implements TemplatesView {

  @InjectPresenter TemplatesPresenter mPaymentPresenter;

  public TemplatesFragment() {
    super(R.layout.fragment_templates);
  }

  public static TemplatesFragment newInstance() {
    Bundle args = new Bundle();
    TemplatesFragment fragment = new TemplatesFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}
