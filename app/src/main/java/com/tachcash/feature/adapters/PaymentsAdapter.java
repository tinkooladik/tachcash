package com.tachcash.feature.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tachcash.R;
import com.tachcash.data.local.model.TemplateEntity;
import com.tachcash.utils.GlideApp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 11/8/2017.
 */

public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.ViewHolder> {

  private ArrayList<TemplateEntity> mListData = new ArrayList<>();
  private PaymentsAdapter.OnItemRemovedListener mOnItemRemovedListener;

  public interface OnItemRemovedListener {
    void onItemRemoved(int pos);
  }

  public PaymentsAdapter(PaymentsAdapter.OnItemRemovedListener listener) {
    mOnItemRemovedListener = listener;
  }

  public void addList(List<TemplateEntity> listData) {
    mListData.clear();
    mListData.addAll(listData);
    notifyDataSetChanged();
  }

  @NonNull @Override
  public PaymentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new PaymentsAdapter.ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull PaymentsAdapter.ViewHolder holder, int position) {
    TemplateEntity template = mListData.get(position);
    GlideApp.with(holder.mIvServiceIcon.getContext())
        .load(template.getIcon())
        .into(holder.mIvServiceIcon);
    holder.mTvSum.setText(
        holder.mIvServiceIcon.getContext().getString(R.string.payment_hrn, template.getAmount()));
    holder.mTvServiceName.setText(template.getServiceName());
    holder.mTvAccount.setText(template.getAccount());
  }

  @Override public int getItemCount() {
    return mListData.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivServiceIcon) ImageView mIvServiceIcon;
    @BindView(R.id.tvServiceName) TextView mTvServiceName;
    @BindView(R.id.tvAccount) TextView mTvAccount;
    @BindView(R.id.tvSum) TextView mTvSum;

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    @OnClick(R.id.ivDelete) public void onClickDelete() {
      mOnItemRemovedListener.onItemRemoved(getAdapterPosition());
      mListData.remove(getAdapterPosition());
      notifyItemRemoved(getAdapterPosition());
    }
  }
}
