package com.tachcash.feature.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;
import com.tachcash.R;
import com.tachcash.base.BaseFragment;
import com.tachcash.data.remote.models.ServiceParentEntity;
import com.tachcash.feature.adapters.ServiceParentAdapter;
import com.tachcash.feature.presenters.CatalogPresenter;
import com.tachcash.feature.views.CatalogView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class CatalogFragment extends BaseFragment implements CatalogView {

  @InjectPresenter CatalogPresenter mCatalogPresenter;

  @BindView(R.id.rvCatalog) RecyclerView mRvCatalog;

  private ServiceParentAdapter mAdapter;

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

    mAdapter = new ServiceParentAdapter();
    mRvCatalog.setLayoutManager(new GridLayoutManager(getContext(), 2));
    mRvCatalog.setAdapter(mAdapter);
    mRvCatalog.addItemDecoration(new GridDividerItemDecoration(
        ContextCompat.getDrawable(Objects.requireNonNull(getContext()),
            R.drawable.list_divider_vertical),
        ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.list_divider),
        2));

    List<ServiceParentEntity> services = new ArrayList<>();
    services.add(new ServiceParentEntity("Перевод на карту"));
    services.add(new ServiceParentEntity("Интернет\n" + "провайдеры"));
    services.add(new ServiceParentEntity("Перевод на карту"));
    services.add(new ServiceParentEntity("Перевод на карту"));
    services.add(new ServiceParentEntity("Интернет\n" + "провайдеры"));
    services.add(new ServiceParentEntity("Перевод на карту"));

    mAdapter.addList(services);
  }
}
