package com.tachcash.base;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alexandra on 11/3/2017.
 */

public class Navigator {

  /* for activity */
  public void finishActivity(@NonNull AppCompatActivity appCompatActivity) {
    appCompatActivity.finish();
  }

  public void startActivity(@NonNull AppCompatActivity appCompatActivity, @NonNull Intent intent) {
    appCompatActivity.startActivity(intent);
  }

  public void startActivityClearStack(@NonNull AppCompatActivity appCompatActivity,
      @NonNull Intent intent) {
    appCompatActivity.finishAffinity();
    appCompatActivity.startActivity(intent);
  }

  /* for fragment */
  public void addFragment(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .add(containerId, fragment)
        .commit();
  }

  public void addFragmentBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .add(containerId, fragment)
        .commit();
  }

  public void addFragmentTag(@NonNull AppCompatActivity appCompatActivity, @IdRes int containerId,
      @NonNull Fragment fragment, @NonNull String fragmentTag) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .add(containerId, fragment, fragmentTag)
        .commit();
  }

  public void addFragmentTagBackStack(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
    appCompatActivity.getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .add(containerId, fragment, fragmentTag)
        .commit();
  }

  public void addChildFragment(@NonNull Fragment parent, @IdRes int containerId,
      @NonNull Fragment child) {
    parent.getChildFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .add(containerId, child)
        .commit();
  }

  public void replaceChildFragment(@NonNull Fragment parent, @IdRes int containerId,
      @NonNull Fragment child) {
    parent.getChildFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .replace(containerId, child)
        .commit();
  }

  public void replaceFragmentTagNotCopy(@NonNull AppCompatActivity appCompatActivity,
      @IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    Fragment fragmentCopy = fragmentManager.findFragmentByTag(fragmentTag);
    if (fragmentCopy == null) {
      fragmentManager.beginTransaction().replace(containerId, fragment, fragmentTag).commit();
    }
  }

  public void clearBackStack(@NonNull AppCompatActivity activity) {
    if (!isEmptyBackStack(activity)) {
      activity.getSupportFragmentManager()
          .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
  }

  public void removeFragmentBackStack(@NonNull AppCompatActivity activity,
      @NonNull String fragmentTag) {
    activity.getSupportFragmentManager()
        .beginTransaction()
        .remove(activity.getSupportFragmentManager().findFragmentByTag(fragmentTag))
        .commit();
    activity.getSupportFragmentManager().popBackStack();
  }

  public boolean isEmptyBackStack(@NonNull AppCompatActivity activity) {
    return activity.getSupportFragmentManager().getBackStackEntryCount() == 0;
  }

  public boolean isSingleItemBackStack(@NonNull AppCompatActivity activity) {
    return activity.getSupportFragmentManager().getBackStackEntryCount() == 0;
  }

  public boolean isFragmentTagExists(@NonNull AppCompatActivity appCompatActivity,
      @NonNull String fragmentTag) {
    FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
    Fragment fragmentCopy = fragmentManager.findFragmentByTag(fragmentTag);
    return fragmentCopy != null;
  }
}
