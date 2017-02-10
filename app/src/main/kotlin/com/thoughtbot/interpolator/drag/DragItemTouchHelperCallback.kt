package com.thoughtbot.interpolator.drag

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.Callback

class DragItemTouchHelperCallback(val adapter: DragItemTouchHelperAdapter) : Callback() {

  override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: ViewHolder?): Int {
    return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.DOWN.or(ItemTouchHelper.UP))
  }

  override fun onMove(view: RecyclerView?, holder: ViewHolder?, target: ViewHolder?): Boolean {
    val fromPos = holder?.adapterPosition
    val toPos = target?.adapterPosition

    if (fromPos != null && toPos != null) {
      adapter.onItemMove(fromPos, toPos)
      return true
    } else {
      return false
    }
  }

  override fun onSwiped(viewHolder: ViewHolder?, direction: Int) {}
}


