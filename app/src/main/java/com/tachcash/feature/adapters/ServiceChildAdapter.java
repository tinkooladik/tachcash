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
import com.tachcash.data.remote.models.ServiceParentEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 11/8/2017.
 */

public class ServiceChildAdapter extends RecyclerView.Adapter<ServiceChildAdapter.ViewHolder> {
  private ArrayList<ServiceParentEntity> mListData = new ArrayList<>();

  public void addList(List<ServiceParentEntity> listData) {
    mListData.clear();
    mListData.addAll(listData);
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
