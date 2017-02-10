package com.thoughtbot.interpolator

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.Interpolator
import android.widget.Button
import org.jetbrains.anko.find
import java.util.regex.Pattern

class DotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  val button by lazy { itemView.find<Button>(R.id.list_item_button) }

  fun bindInterpolator(interpolator: Interpolator) {
    val camelCase = interpolator.javaClass.simpleName
    val asWords = camelCase.split(Pattern.compile("(?<!^)(?=[A-Z])"))
    val title = asWords.joinToString(separator = " ")

    button.text = title
  }

  fun toX(): Float {
    return (itemView.width - button.width).toFloat()
  }

  fun animate(interpolator: Interpolator) {
    val initialX = button.x
    button.animate()
        .x(toX())
        .setInterpolator(interpolator)
        .setDuration(2000)
        .withEndAction {
          button.animate()
              .x(initialX)
              .setInterpolator(interpolator)
              .setDuration(2000)
              .start()
        }
        .start()
  }
}



