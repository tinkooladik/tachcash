package com.tachcash.feature.fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.tachcash.R;
import com.tachcash.base.BaseFragment;
import com.tachcash.data.local.model.TemplateEntity;
import com.tachcash.feature.adapters.PaymentsAdapter;
import com.tachcash.feature.presenters.PaymentPresenter;
import com.tachcash.feature.views.PaymentView;
import java.util.ArrayList;
import java.util.Objects;
import timber.log.Timber;

import static com.tachcash.utils.Constants.TEMPLATE_ENTITY;
import static com.tachcash.utils.Constants.TEMPLATE_ENTITY_LIST;
import static com.tachcash.utils.ViewUtil.dpToPx;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class PaymentFragment extends BaseFragment implements PaymentView {

  @InjectPresenter PaymentPresenter mPaymentPresenter;

  @BindView(R.id.ivBarCode) ImageView mIvBarCode;
  @BindView(R.id.tvCodeNum) TextView mTvCodeNum;
  @BindView(R.id.tvDate) TextView mTvDate;
  @BindView(R.id.tvAmount) TextView mTvAmount;
  @BindView(R.id.rvPayments) RecyclerView mRvPayments;
  @BindView(R.id.clTicket) ConstraintLayout mClTicket;

  private ArrayList<TemplateEntity> mTemplates;
  private int mAmount;
  private PaymentsAdapter mAdapter;

  public PaymentFragment() {
    super(R.layout.fragment_payment);
  }

  public static PaymentFragment newInstance(TemplateEntity templateEntity) {
    Bundle args = new Bundle();
    args.putParcelable(TEMPLATE_ENTITY, templateEntity);
    PaymentFragment fragment = new PaymentFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public static PaymentFragment newInstance(ArrayList<TemplateEntity> templateEntity) {
    Bundle args = new Bundle();
    args.putParcelableArrayList(TEMPLATE_ENTITY_LIST, templateEntity);
    PaymentFragment fragment = new PaymentFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @SuppressLint("DefaultLocale") @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    StringBuilder stringBuilder = new StringBuilder();

    TemplateEntity templateEntity =
        Objects.requireNonNull(getArguments()).getParcelable(TEMPLATE_ENTITY);
    if (templateEntity != null) {
      mTemplates = new ArrayList<>();
      mTemplates.add(templateEntity);
      stringBuilder.append("99")
          .append(String.format("%04d", templateEntity.getService()))
          .append(String.format("%012d", templateEntity.getAccount()))
          .append(String.format("%06d", templateEntity.getAmount()));
      mAmount += templateEntity.getAmount();
    } else {
      mTemplates =
          Objects.requireNonNull(getArguments()).getParcelableArrayList(TEMPLATE_ENTITY_LIST);
      stringBuilder.append("7");
      for (TemplateEntity template : mTemplates) {
        mAmount += template.getAmount();
        stringBuilder.append(String.format("%04d", template.getService()))
            .append(String.format("%06d", template.getAmount()));
      }
      mClTicket.getLayoutParams().height = dpToPx(250);
    }

    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    try {
      BitMatrix bitMatrix =
          multiFormatWriter.encode(stringBuilder.toString(), BarcodeFormat.CODE_128, 1200, 200);
      BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
      Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
      mIvBarCode.setImageBitmap(bitmap);
    } catch (WriterException e) {
      Timber.e(e);
    }

    mTvCodeNum.setText(mTemplates.get(0).getTachcashCode());
    mTvDate.setText(getString(R.string.payment_date, mTemplates.get(0).getDate()));
    mTvAmount.setText(getString(R.string.payment_hrn, mAmount));

    mRvPayments.setLayoutManager(new LinearLayoutManager(getContext()));
    mAdapter = new PaymentsAdapter();
    mRvPayments.setAdapter(mAdapter);
    mAdapter.addList(mTemplates);
  }

  @OnClick(R.id.ivBack) public void onMLlTemplatesClicked() {
    //((MainActivity) Objects.requireNonNull(getActivity())).selectPaymentTab();
    //mNavigator.replaceFragmentTag((MainActivity) Objects.requireNonNull(getActivity()),
    //    R.id.container_main, TemplatesFragment.newInstance(), FRAGMENT_TEMPLATES);
    Objects.requireNonNull(getActivity()).onBackPressed();
  }

  //@OnClick(R.id.llDelete) public void onMLlDeleteClicked() {
  //  mPaymentPresenter.deleteTemplate(mTemplate);
  //  showToastMessage("Шаблон удален!");
  //  Objects.requireNonNull(getActivity()).onBackPressed();
  //}
}
