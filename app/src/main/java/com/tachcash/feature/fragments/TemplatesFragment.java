package com.tachcash.feature.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.tachcash.R;
import com.tachcash.base.BaseFragment;
import com.tachcash.data.local.model.TemplateEntity;
import com.tachcash.feature.activities.MainActivity;
import com.tachcash.feature.adapters.TemplatesAdapter;
import com.tachcash.feature.presenters.TemplatesPresenter;
import com.tachcash.feature.views.TemplatesView;
import com.tachcash.utils.ItemClickSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.tachcash.utils.Constants.FRAGMENT_PAYMENT;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class TemplatesFragment extends BaseFragment implements TemplatesView {

  @InjectPresenter TemplatesPresenter mPaymentPresenter;

  @BindView(R.id.rvTemplates) RecyclerView mRvTemplates;
  @BindView(R.id.tvTitle) TextView mTvTitle;

  private TemplatesAdapter mAdapter;
  private ArrayList<TemplateEntity> mTemplates = new ArrayList<>();
  private int mItemsCount;

  public TemplatesFragment() {
    super(R.layout.fragment_templates);
  }

  public static TemplatesFragment newInstance() {
    Bundle args = new Bundle();
    TemplatesFragment fragment = new TemplatesFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mRvTemplates.setLayoutManager(new LinearLayoutManager(getContext()));
    mAdapter = new TemplatesAdapter(mRvTemplates, (MainActivity) getActivity(), () -> {
      mItemsCount--;
      mTvTitle.setText(getString(R.string.templates_title_descr,
          getResources().getQuantityString(R.plurals.plurals_services, mItemsCount, mItemsCount)));
    });
    mRvTemplates.setAdapter(mAdapter);

    ItemClickSupport.addTo(mRvTemplates).setOnItemClickListener((recyclerView, position, v) -> {
      mAdapter.setSelected(position);
    });
  }

  @Override public void setUpTemplates(List<TemplateEntity> templates) {
    mItemsCount = templates.size();
    mTemplates.clear();
    mTemplates.addAll(templates);
    mAdapter.addList(mTemplates);
    mTvTitle.setText(getString(R.string.templates_title_descr,
        getResources().getQuantityString(R.plurals.plurals_services, mItemsCount, mItemsCount)));
  }

  @OnClick(R.id.btnPayAll) public void onClickPayAll() {
    if (mTemplates.size() > 0) {
      ArrayList<TemplateEntity> templates = new ArrayList<>();
      int size = mTemplates.size() < 3 ? mTemplates.size() : 3;
      for (int i = 0; i < size; i++) {
        templates.add(mTemplates.get(i));
      }
      mNavigator.addFragmentTagBackStack((MainActivity) Objects.requireNonNull(getActivity()),
          R.id.container_main, PaymentFragment.newInstance(templates), FRAGMENT_PAYMENT);
    } else {
      showToastMessage("Ваша корзина пуста!");
    }
  }
}
