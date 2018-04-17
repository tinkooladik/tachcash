package com.tachcash.feature.presenters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.widget.Toast;
import com.arellomobile.mvp.InjectViewState;
import com.tachcash.App;
import com.tachcash.R;
import com.tachcash.base.BasePresenter;
import com.tachcash.feature.views.MapNearView;
import com.tedpark.tedpermission.rx2.TedRx2Permission;
import timber.log.Timber;

/**
 * Created by Alexandra on 11/8/2017.
 */

@InjectViewState public class MapPresenter extends BasePresenter<MapNearView> {

  @Override protected void inject() {
    App.getAppComponent().inject(this);
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();

    checkPermission();
  }

  @SuppressLint("CheckResult") private void checkPermission() {
    TedRx2Permission.with(mContext)
        .setRationaleTitle(R.string.permission_title)
        .setRationaleMessage(R.string.permission_descr)
        .setRationaleConfirmText(R.string.permission_confirm)
        .setDeniedTitle(R.string.permission_denied_title)
        .setDeniedMessage(R.string.permission_denied_descr)
        .setDeniedCloseButtonText(R.string.permission_close)
        .setGotoSettingButtonText(R.string.permission_settings)
        .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION)
        .request()
        .subscribe(tedPermissionResult -> {
          if (tedPermissionResult.isGranted()) {
            getViewState().setUpMap();
          } else {
            Toast.makeText(mContext,
                "Permission Denied\n" + tedPermissionResult.getDeniedPermissions().toString(),
                Toast.LENGTH_SHORT).show();
            getViewState().showWarning();
          }
        }, Timber::e, () -> {
        });
  }
}
