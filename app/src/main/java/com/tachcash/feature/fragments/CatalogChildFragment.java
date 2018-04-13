package com.tachcash.feature.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.tachcash.R;
import com.tachcash.base.BaseFragment;
import com.tachcash.data.remote.models.ServiceChildren;
import com.tachcash.data.remote.models.ServiceParent;
import com.tachcash.feature.adapters.ServiceChildAdapter;
import com.tachcash.feature.presenters.CatalogChildPresenter;
import com.tachcash.feature.views.CatalogChildView;
import com.tachcash.utils.ItemClickSupport;
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
  @BindView(R.id.pbProgress) ProgressBar mProgressBar;

  private ServiceChildAdapter mAdapter;
  private ServiceParent mServiceParent;

  public CatalogChildFragment() {
    super(R.layout.fragment_catalog_child);
  }

  public static CatalogChildFragment newInstance(ServiceParent serviceParent) {
    Bundle args = new Bundle();
    args.putParcelable(SERVICE_PARENT, serviceParent);
    CatalogChildFragment fragment = new CatalogChildFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mServiceParent = Objects.requireNonNull(getArguments()).getParcelable(SERVICE_PARENT);
    mTvTitle.setText(Objects.requireNonNull(mServiceParent).getTitle());

    mCatalogChildPresenter.getAllServicesChild(mServiceParent.getId());

    mAdapter = new ServiceChildAdapter(mRvCatalog);
    mRvCatalog.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvCatalog.setAdapter(mAdapter);

    ItemClickSupport.addTo(mRvCatalog).setOnItemClickListener((recyclerView, position, v) -> {
      mAdapter.setSelected(position);
    });
  }

  @OnClick(R.id.ivBack) public void onViewClicked() {
    Objects.requireNonNull(getActivity()).onBackPressed();
  }

  @Override public void setProgressVisible(boolean visible) {
    mProgressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
  }

  @Override public void setUpServices(List<ServiceChildren> services) {
    mAdapter.addList(services);
  }

  @Override public void showDialogError(String error) {
    showErrorMessage(error);
  }
}
