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
import com.tachcash.App;
import com.tachcash.R;
import com.tachcash.base.Navigator;
import com.tachcash.data.DataManager;
import com.tachcash.data.local.model.TemplateEntity;
import com.tachcash.utils.GlideApp;
import com.tachcash.utils.RxBus;
import com.tachcash.utils.RxBusHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import net.cachapa.expandablelayout.ExpandableLayout;

import static com.tachcash.utils.Converters.getFormattedDateFromTimestamp;
import static com.tachcash.utils.ViewUtil.dpToPx;

/**
 * Created by Alexandra on 11/8/2017.
 */

public class TemplatesAdapter extends RecyclerView.Adapter<TemplatesAdapter.ViewHolder> {

  @Inject DataManager mDataManager;
  @Inject RxBus mRxBus;

  private ArrayList<TemplateEntity> mListData = new ArrayList<>();
  private RecyclerView mRecyclerView;
  private int mLastPos;
  private int mPadding = dpToPx(16);
  private int mVerticalPadding = dpToPx(4);
  private Navigator mNavigator;
  private AppCompatActivity mActivity;

  public TemplatesAdapter(RecyclerView recyclerView, Navigator navigator,
      AppCompatActivity activity) {
    App.getAppComponent().inject(this);
    mRecyclerView = recyclerView;
    mNavigator = navigator;
    mActivity = activity;
  }

  public void addList(List<TemplateEntity> listData) {
    mListData.clear();
    mListData.addAll(listData);
    mLastPos = mListData.size() - 1;
    notifyDataSetChanged();
  }

  @NonNull @Override
  public TemplatesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new TemplatesAdapter.ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_template, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull TemplatesAdapter.ViewHolder holder, int position) {
    TemplateEntity template = mListData.get(position);
    holder.mTvName.setText(template.getServiceName());
    GlideApp.with(holder.mIvIcon.getContext()).load(template.getIcon()).into(holder.mIvIcon);
    holder.mTvAmount.setText(mActivity.getString(R.string.templates_amount, template.getAmount()));
    holder.mTvAccount.setText(
        mActivity.getString(R.string.templates_account, template.getAccount()));
    holder.mEtAmount.setText(String.valueOf(template.getAmount()));
    holder.mEtAccount.setText(String.valueOf(template.getAccount()));

    if (position == 0) {
      holder.mClParent.setPadding(mPadding, mPadding, mPadding, mVerticalPadding);
    } else if (position == mLastPos) {
      holder.mClParent.setPadding(mPadding, mVerticalPadding, mPadding, mPadding);
    } else {
      holder.mClParent.setPadding(mPadding, mVerticalPadding, mPadding, mVerticalPadding);
    }
  }

  public void setSelected(int selectedPos) {
    TemplatesAdapter.ViewHolder holder =
        (TemplatesAdapter.ViewHolder) mRecyclerView.findViewHolderForAdapterPosition(selectedPos);
    GlideApp.with(mRecyclerView.getContext())
        .load(holder.mExpandableLayout.isExpanded() ? R.drawable.ic_arrow_collapsed
            : R.drawable.ic_arrow_expanded)
        .into(holder.mIvArrow);
    holder.mClInfo.setVisibility(holder.mExpandableLayout.isExpanded() ? View.VISIBLE : View.GONE);
    holder.mExpandableLayout.toggle();
  }

  @Override public int getItemCount() {
    return mListData.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ivIcon) AppCompatImageView mIvIcon;
    @BindView(R.id.ivArrow) AppCompatImageView mIvArrow;
    @BindView(R.id.tvName) TextView mTvName;
    @BindView(R.id.tvAmount) TextView mTvAmount;
    @BindView(R.id.tvAccount) TextView mTvAccount;
    @BindView(R.id.expandable_layout) ExpandableLayout mExpandableLayout;
    @BindView(R.id.clParent) ConstraintLayout mClParent;
    @BindView(R.id.clInfo) ConstraintLayout mClInfo;
    @BindView(R.id.etAccount) EditText mEtAccount;
    @BindView(R.id.etAmount) EditText mEtAmount;

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }

    @OnClick({ R.id.btnDelete, R.id.ivDelete }) public void onClickDelete() {
      mDataManager.deleteTemplate(mListData.get(getAdapterPosition()));
      mRxBus.post(new RxBusHelper.UpdateBadgeCount());
      mListData.remove(getAdapterPosition());
      notifyItemRemoved(getAdapterPosition());
    }

    @OnClick(R.id.btnSave) public void onCLickSave() {
      mDataManager.updateTemplate(mListData.get(getAdapterPosition()), createTemplate());
      mListData.get(getAdapterPosition())
          .setAccount(Long.parseLong(mEtAccount.getText().toString()));
      mListData.get(getAdapterPosition())
          .setAmount(Integer.parseInt(mEtAmount.getText().toString()));
      notifyItemChanged(getAdapterPosition());
      Toast.makeText(mRecyclerView.getContext(), "Шаблон сохранен!", Toast.LENGTH_LONG).show();
    }

    private TemplateEntity createTemplate() {
      TemplateEntity templateEntity = new TemplateEntity();
      templateEntity.setService(mListData.get(getAdapterPosition()).getService());
      templateEntity.setAccount(Long.parseLong(mEtAccount.getText().toString()));
      templateEntity.setAmount(Integer.parseInt(mEtAmount.getText().toString()));
      templateEntity.setServiceName(mListData.get(getAdapterPosition()).getServiceName());
      templateEntity.setDate(getFormattedDateFromTimestamp(System.currentTimeMillis()));
      templateEntity.setIcon(mListData.get(getAdapterPosition()).getIcon());
      templateEntity.setTachcashCode(String.valueOf(new Random().nextInt(5000)));
      return templateEntity;
    }
  }
}
