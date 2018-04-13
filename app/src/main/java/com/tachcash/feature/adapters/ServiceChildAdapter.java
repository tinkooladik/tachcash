package com.tachcash.feature.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tachcash.R;
import com.tachcash.base.Navigator;
import com.tachcash.data.local.model.TemplateEntity;
import com.tachcash.data.remote.models.ServiceChildren;
import com.tachcash.feature.activities.MainActivity;
import com.tachcash.feature.fragments.PaymentFragment;
import com.tachcash.utils.GlideApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.cachapa.expandablelayout.ExpandableLayout;

import static com.tachcash.utils.Constants.FRAGMENT_PAYMENT;
import static com.tachcash.utils.Converters.getFormattedDateFromTimestamp;
import static com.tachcash.utils.ViewUtil.dpToPx;

/**
 * Created by Alexandra on 11/8/2017.
 */

public class ServiceChildAdapter extends RecyclerView.Adapter<ServiceChildAdapter.ViewHolder> {

  private ArrayList<ServiceChildren> mListData = new ArrayList<>();
  private RecyclerView mRecyclerView;
  private int mLastPos;
  private int mPadding = dpToPx(16);
  private int mVerticalPadding = dpToPx(4);
  private Navigator mNavigator;
  private AppCompatActivity mActivity;

  public ServiceChildAdapter(RecyclerView recyclerView, Navigator navigator,
      AppCompatActivity activity) {
    mRecyclerView = recyclerView;
    mNavigator = navigator;
    mActivity = activity;
  }

  public void addList(List<ServiceChildren> listData) {
    mListData.clear();
    mListData.addAll(listData);
    mLastPos = mListData.size() - 1;
    notifyDataSetChanged();
  }

  @NonNull @Override
  public ServiceChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    return new ServiceChildAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_service_child, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ServiceChildAdapter.ViewHolder holder, int position) {
    holder.mTvName.setText(mListData.get(position).getName());
    GlideApp.with(holder.mIvIcon.getContext())
        .load(mListData.get(position).getLogo())
        .into(holder.mIvIcon);

    if (position == 0) {
      holder.mClParent.setPadding(mPadding, mPadding, mPadding, mVerticalPadding);
    } else if (position == mLastPos) {
      holder.mClParent.setPadding(mPadding, mVerticalPadding, mPadding, mPadding);
    } else {
      holder.mClParent.setPadding(mPadding, mVerticalPadding, mPadding, mVerticalPadding);
    }
  }

  public void setSelected(int selectedPos) {
    ViewHolder holder = (ViewHolder) mRecyclerView.findViewHolderForAdapterPosition(selectedPos);
    GlideApp.with(mRecyclerView.getContext())
        .load(holder.mExpandableLayout.isExpanded() ? R.drawable.ic_arrow_collapsed
            : R.drawable.ic_arrow_expanded)
        .into(holder.mIvArrow);
    holder.mExpandableLayout.toggle();
  }

  @Override public int getItemCount() {
    return mListData.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivIcon) AppCompatImageView mIvIcon;
    @BindView(R.id.ivArrow) AppCompatImageView mIvArrow;
    @BindView(R.id.tvName) TextView mTvName;
    @BindView(R.id.expandable_layout) ExpandableLayout mExpandableLayout;
    @BindView(R.id.clParent) ConstraintLayout mClParent;
    @BindView(R.id.etAccount) EditText mEtAccount;
    @BindView(R.id.etAmount) EditText mEtAmount;

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btnCreateTemplate) public void onClickTemplate() {
      Toast.makeText(mRecyclerView.getContext(), "Шаблон сохранен!", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnGoToPayment) public void onCLickPayment() {
      ((MainActivity) mActivity).selectPaymentTab();
      TemplateEntity templateEntity = new TemplateEntity();
      templateEntity.setService(mListData.get(getAdapterPosition()).getId());
      templateEntity.setAccount(Long.parseLong(mEtAccount.getText().toString()));
      templateEntity.setAmount(Integer.parseInt(mEtAmount.getText().toString()));
      templateEntity.setServiceName(mListData.get(getAdapterPosition()).getName());
      templateEntity.setDate(getFormattedDateFromTimestamp(System.currentTimeMillis()));
      templateEntity.setIcon(mListData.get(getAdapterPosition()).getLogo());
      templateEntity.setTachcashCode(String.valueOf(new Random().nextInt(5000)));
      mNavigator.replaceFragmentTagNotCopy(mActivity, R.id.container_main,
          PaymentFragment.newInstance(templateEntity), FRAGMENT_PAYMENT);
    }
  }
}
