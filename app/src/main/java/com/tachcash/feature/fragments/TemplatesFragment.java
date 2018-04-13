package com.tachcash.feature.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import java.util.Objects;

import static com.tachcash.utils.Constants.FRAGMENT_PAYMENT;

/**
 * Created by Alexandra on 11.04.2018.
 */
public class TemplatesFragment extends BaseFragment implements TemplatesView {

  @InjectPresenter TemplatesPresenter mPaymentPresenter;

  @BindView(R.id.rvTemplates) RecyclerView mRvTemplates;

  private TemplatesAdapter mAdapter;

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
    mAdapter = new TemplatesAdapter();
    mRvTemplates.setAdapter(mAdapter);

    ArrayList<TemplateEntity> templateEntities = new ArrayList<>(mPaymentPresenter.getTemplates());
    mAdapter.addList(templateEntities);

    ItemClickSupport.addTo(mRvTemplates)
        .setOnItemClickListener((recyclerView, position, v) -> mNavigator.replaceFragmentTagNotCopy(
            (MainActivity) Objects.requireNonNull(getActivity()), R.id.container_main,
            PaymentFragment.newInstance(templateEntities.get(position)), FRAGMENT_PAYMENT));
  }
}
