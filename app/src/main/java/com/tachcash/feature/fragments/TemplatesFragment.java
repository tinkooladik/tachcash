package com.tachcash.feature.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
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

/**
 * Created by Alexandra on 11.04.2018.
 */
public class TemplatesFragment extends BaseFragment implements TemplatesView {

  @InjectPresenter TemplatesPresenter mPaymentPresenter;

  @BindView(R.id.rvTemplates) RecyclerView mRvTemplates;
  @BindView(R.id.tvTitle) TextView mTvTitle;

  private TemplatesAdapter mAdapter;
  private ArrayList<TemplateEntity> mTemplates = new ArrayList<>();

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
    mAdapter = new TemplatesAdapter(mRvTemplates, mNavigator, (MainActivity) getActivity());
    mRvTemplates.setAdapter(mAdapter);

    ItemClickSupport.addTo(mRvTemplates).setOnItemClickListener((recyclerView, position, v) -> {
      mAdapter.setSelected(position);
    });
  }

  @Override public void setUpTemplates(List<TemplateEntity> templates) {
    mTemplates.clear();
    mTemplates.addAll(templates);
    mAdapter.addList(mTemplates);
    mTvTitle.setText(getString(R.string.templates_title_descr, templates.size()));
  }
}
