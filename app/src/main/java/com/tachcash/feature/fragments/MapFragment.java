package com.tachcash.feature.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.tachcash.R;
import com.tachcash.base.BaseFragment;
import com.tachcash.feature.presenters.MapPresenter;
import com.tachcash.feature.views.MapView;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class MapFragment extends BaseFragment implements MapView {

  @InjectPresenter MapPresenter mMapPresenter;

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
}
