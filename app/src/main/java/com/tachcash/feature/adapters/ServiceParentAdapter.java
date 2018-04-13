package com.tachcash.feature.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tachcash.R;
import com.tachcash.data.remote.models.ServiceParent;
import com.tachcash.utils.GlideApp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 11/8/2017.
 */

public class ServiceParentAdapter extends RecyclerView.Adapter<ServiceParentAdapter.ViewHolder> {
  private ArrayList<ServiceParent> mListData = new ArrayList<>();

  public void addList(List<ServiceParent> listData) {
    mListData.clear();
    mListData.addAll(listData);
    notifyDataSetChanged();
  }

  @NonNull @Override
  public ServiceParentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    return new ServiceParentAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_service_parent, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ServiceParentAdapter.ViewHolder holder, int position) {
    holder.mTvName.setText(mListData.get(position).getTitle());
    GlideApp.with(holder.mIvIcon.getContext())
        .load(mListData.get(position).getLogo())
        .into(holder.mIvIcon);
  }

  public ServiceParent getEntity(int position) {
    return mListData.get(position);
  }

  @Override public int getItemCount() {
    return mListData.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivIcon) AppCompatImageView mIvIcon;
    @BindView(R.id.tvName) TextView mTvName;

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
