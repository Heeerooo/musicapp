package com.franck.cedric.musicapp.ui

import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.RecyclerView


abstract class AnimatedAdapter<V : RecyclerView.ViewHolder> : RecyclerView.Adapter<V>() {

    protected var animationEnabled = true

    override fun onViewAttachedToWindow(holder: V) {
        if (animationEnabled) {
            with(holder.itemView) {
                alpha = 0f
                scaleY = 0.75f
                scaleX = 0.75f
                animate().alpha(1f).scaleX(1f).scaleY(1f)
                    .setInterpolator(DecelerateInterpolator()).start()
            }
        }
    }
}