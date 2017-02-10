package com.thoughtbot.interpolator

import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import com.thoughtbot.interpolator.drag.DragItemTouchHelperAdapter
import java.util.Collections

class DotAdapter : RecyclerView.Adapter<DotViewHolder>(), DragItemTouchHelperAdapter {

  val interpolators: List<Interpolator> by lazy {
    listOf(
        AccelerateDecelerateInterpolator(),
        AccelerateInterpolator(),
        AnticipateInterpolator(),
        AnticipateOvershootInterpolator(),
        BounceInterpolator(),
        DecelerateInterpolator(),
        FastOutLinearInInterpolator(),
        FastOutSlowInInterpolator(),
        LinearInterpolator(),
        LinearOutSlowInInterpolator(),
        OvershootInterpolator()
    )
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DotViewHolder {
    val view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_dot, parent, false)
    return DotViewHolder(view)
  }

  override fun onBindViewHolder(holder: DotViewHolder?, position: Int) {
    holder?.bindInterpolator(interpolators[position])
  }

  override fun getItemCount(): Int = interpolators.size

  override fun onItemMove(fromPosition: Int, toPosition: Int) {
    if (fromPosition < toPosition) {
      for (i in fromPosition..toPosition - 1) {
        Collections.swap(interpolators, i, i + 1)
      }
    } else {
      for (i in fromPosition downTo toPosition + 1) {
        Collections.swap(interpolators, i, i - 1)
      }
    }
    notifyItemMoved(fromPosition, toPosition)
  }
}

