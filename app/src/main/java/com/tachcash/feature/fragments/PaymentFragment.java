package com.tachcash.feature.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.tachcash.R;
import com.tachcash.base.BaseFragment;
import com.tachcash.feature.presenters.PaymentPresenter;
import com.tachcash.feature.views.PaymentView;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class PaymentFragment extends BaseFragment implements PaymentView {

  @InjectPresenter PaymentPresenter mPaymentPresenter;

  public PaymentFragment() {
    super(R.layout.fragment_payment);
  }

  public static PaymentFragment newInstance() {
    Bundle args = new Bundle();
    PaymentFragment fragment = new PaymentFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}
