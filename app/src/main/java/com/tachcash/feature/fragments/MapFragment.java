package com.tachcash.feature.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tachcash.R;
import com.tachcash.base.BaseFragment;
import com.tachcash.feature.activities.MainActivity;
import com.tachcash.feature.presenters.MapPresenter;
import com.tachcash.feature.views.MapNearView;
import java.util.Objects;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class MapFragment extends BaseFragment implements MapNearView {

  @InjectPresenter MapPresenter mMapPresenter;

  @BindView(R.id.clWarning) ConstraintLayout mClWarning;

  private GoogleMap mGoogleMap;

  public MapFragment() {
    super(R.layout.fragment_map);
  }

  public static MapFragment newInstance() {
    Bundle args = new Bundle();
    MapFragment fragment = new MapFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @SuppressLint("MissingPermission") @Override public void setUpMap() {
    mClWarning.setVisibility(View.GONE);
    ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(
        googleMap -> {
          mGoogleMap = googleMap;
          mGoogleMap.setMyLocationEnabled(true);

          LatLng kyiv = new LatLng(50.45466, 30.5238);
          mGoogleMap.addMarker(
              new MarkerOptions().position(kyiv).title("Marker 1").snippet("Marker Description"));
          mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.4, 30.7))
              .title("Marker 2")
              .snippet("Marker Description"));
          mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.55, 30.0))
              .title("Marker 3")
              .snippet("Marker Description"));
          mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.49, 30.58))
              .title("Marker 4")
              .snippet("Marker Description"));
          mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.22, 30.47))
              .title("Marker 5")
              .snippet("Marker Description"));
          mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.29, 30.8))
              .title("Marker 6")
              .snippet("Marker Description"));
          mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.35, 30.43))
              .title("Marker 7")
              .snippet("Marker Description"));
          mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.2, 30.4))
              .title("Marker 8")
              .snippet("Marker Description"));
          mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.45, 30.2))
              .title("Marker 9")
              .snippet("Marker Description"));

          CameraPosition cameraPosition = new CameraPosition.Builder().target(kyiv).zoom(9).build();
          mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        });
  }

  @Override public void showWarning() {
    mClWarning.setVisibility(View.VISIBLE);
  }

  @OnClick(R.id.tvSettings) public void onSettingsClick() {
    Intent intent = new Intent();
    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    Uri uri =
        Uri.fromParts("package", Objects.requireNonNull(getActivity()).getPackageName(), null);
    intent.setData(uri);
    mNavigator.startActivity((MainActivity) getActivity(), intent);
  }
}
