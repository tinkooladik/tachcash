package com.tachcash.feature.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tachcash.R;
import com.tachcash.data.local.model.TemplateEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 11/8/2017.
 */

public class TemplatesAdapter extends RecyclerView.Adapter<TemplatesAdapter.ViewHolder> {

  private ArrayList<TemplateEntity> mListData = new ArrayList<>();

  public void addList(List<TemplateEntity> listData) {
    mListData.clear();
    mListData.addAll(listData);
    notifyDataSetChanged();
  }

  @NonNull @Override
  public TemplatesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new TemplatesAdapter.ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_template, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull TemplatesAdapter.ViewHolder holder, int position) {
    holder.mTvName.setText(
        mListData.get(position).getServiceName() + " " + mListData.get(position).getAmount());
  }

  @Override public int getItemCount() {
    return mListData.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvName) TextView mTvName;

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
