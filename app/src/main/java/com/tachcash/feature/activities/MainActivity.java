package com.tachcash.feature.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.tachcash.R;
import com.tachcash.base.BaseActivity;
import com.tachcash.feature.presenters.MainPresenter;
import com.tachcash.feature.views.MainView;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainView {

  @InjectPresenter MainPresenter mMainPresenter;

  @BindView(R.id.iv) ImageView mIv;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    String text = "9911111222222222222333333"; // Whatever you need to encode in the QR code
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    try {
      BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.CODE_39, 200, 200);
      BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
      Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
      mIv.setImageBitmap(bitmap);
    } catch (WriterException e) {
      Timber.e(e);
    }
  }
}
