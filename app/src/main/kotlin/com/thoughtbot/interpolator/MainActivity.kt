package com.thoughtbot.interpolator

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.thoughtbot.interpolator.drag.DragItemTouchHelper
import org.jetbrains.anko.find
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class MainActivity : AppCompatActivity() {

  val recyclerView by lazy { find<RecyclerView>(R.id.recycler_view) }
  val adapter by lazy { DotAdapter() }
  val touchHelper by lazy { DragItemTouchHelper(adapter) }
  val layoutManager by lazy { LinearLayoutManager(this) }
  val play by lazy { find<FloatingActionButton>(R.id.play) }

  override fun attachBaseContext(newBase: Context?) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initCustomFont()

    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = adapter
    touchHelper.attachToRecyclerView(recyclerView)

    play.setOnClickListener {
      animateDots()
    }
  }

  private fun animateDots() {
    val first = layoutManager.findFirstVisibleItemPosition()
    val last = layoutManager.findLastVisibleItemPosition()

    for (i in first..last) {
      val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) as DotViewHolder
      viewHolder.animate(adapter.interpolators[i])
    }
  }

  private fun initCustomFont() {
    CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
        .setDefaultFontPath("ProximaNova-Semibold.otf")
        .setFontAttrId(R.attr.fontPath)
        .build()
    )
  }
}

