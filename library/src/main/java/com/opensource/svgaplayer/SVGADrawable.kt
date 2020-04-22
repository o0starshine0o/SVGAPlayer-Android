package com.opensource.svgaplayer

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.opensource.svgaplayer.drawer.SVGACanvasDrawer

class SVGADrawable @JvmOverloads constructor(val videoItem: SVGAVideoEntity, val dynamicItem: SVGADynamicEntity = SVGADynamicEntity(),
                                             internal var scaleType: ImageView.ScaleType = ImageView.ScaleType.MATRIX) : Drawable() {

    var cleared = true
        internal set(value) {
            if (field == value) {
                return
            }
            field = value
            invalidateSelf()
        }

    var currentFrame = 0
        internal set(value) {
            if (field == value) {
                return
            }
            field = value
            invalidateSelf()
        }

    private val drawer = SVGACanvasDrawer(videoItem, dynamicItem)

    override fun draw(canvas: Canvas?) {
        if (cleared) {
            return
        }
        canvas?.let {
            drawer.drawFrame(it, currentFrame, scaleType)
        }
    }

    override fun setAlpha(alpha: Int) {}

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

}