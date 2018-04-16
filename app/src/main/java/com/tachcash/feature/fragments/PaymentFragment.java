package com.tachcash.feature.fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.tachcash.feature.activities.MainActivity;
import com.tachcash.feature.presenters.PaymentPresenter;
import com.tachcash.feature.views.PaymentView;
import com.tachcash.utils.GlideApp;
import java.util.Objects;
import timber.log.Timber;

import static com.tachcash.utils.Constants.FRAGMENT_TEMPLATES;
import static com.tachcash.utils.Constants.TEMPLATE_ENTITY;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class PaymentFragment extends BaseFragment implements PaymentView {

  @InjectPresenter PaymentPresenter mPaymentPresenter;

  @BindView(R.id.ivBarCode) ImageView mIvBarCode;
  @BindView(R.id.tvCodeNum) TextView mTvCodeNum;
  @BindView(R.id.tvDate) TextView mTvDate;
  @BindView(R.id.ivServiceIcon) ImageView mIvServiceIcon;
  @BindView(R.id.tvServiceName) TextView mTvServiceName;
  @BindView(R.id.tvAccount) TextView mTvAccount;
  @BindView(R.id.tvSum) TextView mTvSum;
  @BindView(R.id.tvAmount) TextView mTvAmount;

  private TemplateEntity mTemplate;

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

  @SuppressLint("DefaultLocale") @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mTemplate = Objects.requireNonNull(getArguments()).getParcelable(TEMPLATE_ENTITY);

    StringBuilder stringBuilder = new StringBuilder("99");
    stringBuilder.append(String.format("%04d", mTemplate.getService()))
        .append(String.format("%012d", mTemplate.getAccount()))
        .append(String.format("%06d", mTemplate.getAmount()));

    Timber.e(String.format("%04d", mTemplate.getService()));
    Timber.e(String.format("%012d", mTemplate.getAccount()));
    Timber.e(String.format("%06d", mTemplate.getAmount()));
    Timber.e(stringBuilder.toString());

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

    mTvCodeNum.setText(mTemplate.getTachcashCode());
    mTvDate.setText(getString(R.string.payment_date, mTemplate.getDate()));
    GlideApp.with(this).load(mTemplate.getIcon()).into(mIvServiceIcon);
    mTvAmount.setText(getString(R.string.payment_hrn, mTemplate.getAmount()));
    mTvSum.setText(getString(R.string.payment_hrn, mTemplate.getAmount()));
    mTvServiceName.setText(mTemplate.getServiceName());
    mTvAccount.setText(String.valueOf(mTemplate.getAccount()));
  }

  @OnClick(R.id.llCreate) public void onMLlCreateClicked() {
    mPaymentPresenter.saveTemplate(mTemplate);
    showToastMessage("Шаблон сохранен!");
  }

  @OnClick(R.id.llTemplates) public void onMLlTemplatesClicked() {
    ((MainActivity) Objects.requireNonNull(getActivity())).selectPaymentTab();
    mNavigator.replaceFragmentTag((MainActivity) Objects.requireNonNull(getActivity()),
        R.id.container_main, TemplatesFragment.newInstance(), FRAGMENT_TEMPLATES);
  }

  @OnClick(R.id.llDelete) public void onMLlDeleteClicked() {
    mPaymentPresenter.deleteTemplate(mTemplate);
    showToastMessage("Шаблон удален!");
    Objects.requireNonNull(getActivity()).onBackPressed();
  }
}
