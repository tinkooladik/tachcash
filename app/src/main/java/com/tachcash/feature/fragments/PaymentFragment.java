package com.tachcash.feature.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.tachcash.R;
import com.tachcash.base.BaseFragment;
import com.tachcash.feature.presenters.PaymentPresenter;
import com.tachcash.feature.views.PaymentView;
import timber.log.Timber;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class PaymentFragment extends BaseFragment implements PaymentView {

  @InjectPresenter PaymentPresenter mPaymentPresenter;

  @BindView(R.id.ivBarCode) ImageView mIvBarCode;

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

    String text = "9911111222222222222333333";
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    try {
      BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.CODE_128, 1200, 200);
      BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
      Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
      mIvBarCode.setImageBitmap(bitmap);
    } catch (WriterException e) {
      Timber.e(e);
    }
  }
}
