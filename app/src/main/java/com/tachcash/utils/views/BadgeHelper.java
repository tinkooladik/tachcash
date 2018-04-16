package com.tachcash.utils.views;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.tachcash.R;

/**
 * Created by Alexandra on 16.04.2018.
 */
public class BadgeHelper {

  public static void addBadge(BottomNavigationView navigationView, int index) {
    BottomNavigationMenuView bottomNavigationMenuView =
        (BottomNavigationMenuView) navigationView.getChildAt(0);
    View v = bottomNavigationMenuView.getChildAt(index);
    BottomNavigationItemView itemView = (BottomNavigationItemView) v;

    View badge = LayoutInflater.from(navigationView.getContext())
        .inflate(R.layout.badge, bottomNavigationMenuView, false);

    itemView.addView(badge);
  }

  public static void updateBadge(BottomNavigationView navigationView, int index, int count) {
    BottomNavigationMenuView bottomNavigationMenuView =
        (BottomNavigationMenuView) navigationView.getChildAt(0);
    View v = bottomNavigationMenuView.getChildAt(index);
    BottomNavigationItemView itemView = (BottomNavigationItemView) v;
    if (count > 0) {
      itemView.findViewById(R.id.badge).setVisibility(View.VISIBLE);
      ((TextView) itemView.findViewById(R.id.badge)).setText(String.valueOf(count));
    } else {
      ((TextView) itemView.findViewById(R.id.badge)).setText("");
      itemView.findViewById(R.id.badge).setVisibility(View.GONE);
    }
  }
}
