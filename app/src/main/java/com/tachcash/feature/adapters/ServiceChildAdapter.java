package com.tachcash.feature.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tachcash.R;
import com.tachcash.data.remote.models.ServiceParentEntity;
import com.tachcash.utils.GlideApp;
import java.util.ArrayList;
import java.util.List;
import net.cachapa.expandablelayout.ExpandableLayout;

import static com.tachcash.utils.ViewUtil.dpToPx;

/**
 * Created by Alexandra on 11/8/2017.
 */

public class ServiceChildAdapter extends RecyclerView.Adapter<ServiceChildAdapter.ViewHolder> {

  private ArrayList<ServiceParentEntity> mListData = new ArrayList<>();
  private RecyclerView mRecyclerView;
  private int mLastPos;
  private int mPadding = dpToPx(16);
  private int mVerticalPadding = dpToPx(4);

  public ServiceChildAdapter(RecyclerView recyclerView) {
    mRecyclerView = recyclerView;
  }

  public void addList(List<ServiceParentEntity> listData) {
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

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btnCreateTemplate) public void onClickTemplate() {

    }

    @OnClick(R.id.btnGoToPayment) public void onCLickPayment() {

    }
  }
}
