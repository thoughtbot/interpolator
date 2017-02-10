package com.thoughtbot.interpolator.drag

import android.support.v7.widget.helper.ItemTouchHelper

class DragItemTouchHelper(adapter: DragItemTouchHelperAdapter) :
    ItemTouchHelper(DragItemTouchHelperCallback(adapter))

