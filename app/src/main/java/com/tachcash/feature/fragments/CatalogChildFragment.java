package com.tachcash.feature.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;
import com.tachcash.R;
import com.tachcash.base.BaseFragment;
import com.tachcash.data.remote.models.ServiceParentEntity;
import com.tachcash.feature.adapters.ServiceChildAdapter;
import com.tachcash.feature.presenters.CatalogChildPresenter;
import com.tachcash.feature.views.CatalogChildView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.tachcash.utils.Constants.SERVICE_PARENT;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class CatalogChildFragment extends BaseFragment implements CatalogChildView {

  @InjectPresenter CatalogChildPresenter mCatalogChildPresenter;

  @BindView(R.id.rvCatalogChild) RecyclerView mRvCatalog;
  @BindView(R.id.tvServiceName) TextView mTvTitle;

  private ServiceChildAdapter mAdapter;
  private ServiceParentEntity mServiceParentEntity;

  public CatalogChildFragment() {
    super(R.layout.fragment_catalog_child);
  }

  public static CatalogChildFragment newInstance(ServiceParentEntity serviceParentEntity) {
    Bundle args = new Bundle();
    args.putParcelable(SERVICE_PARENT, serviceParentEntity);
    CatalogChildFragment fragment = new CatalogChildFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mServiceParentEntity = Objects.requireNonNull(getArguments()).getParcelable(SERVICE_PARENT);
    mTvTitle.setText(Objects.requireNonNull(mServiceParentEntity).getName());

    mAdapter = new ServiceChildAdapter();
    mRvCatalog.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvCatalog.setAdapter(mAdapter);
    mRvCatalog.addItemDecoration(new GridDividerItemDecoration(
        ContextCompat.getDrawable(Objects.requireNonNull(getContext()),
            R.drawable.list_divider_vertical),
        ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.list_divider),
        2));

    List<ServiceParentEntity> services = new ArrayList<>();
    services.add(new ServiceParentEntity("Воля"));
    services.add(new ServiceParentEntity("Воля-кабель"));
    services.add(new ServiceParentEntity("Воля"));
    services.add(new ServiceParentEntity("Воля"));
    services.add(new ServiceParentEntity("Воля-кабель"));
    services.add(new ServiceParentEntity("Воля-кабель"));
    services.add(new ServiceParentEntity("Воля"));
    services.add(new ServiceParentEntity("Воля"));

    mAdapter.addList(services);
  }

  @OnClick(R.id.ivBack) public void onViewClicked() {
    Objects.requireNonNull(getActivity()).onBackPressed();
  }
}
