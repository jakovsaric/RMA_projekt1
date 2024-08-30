//package com.jakovsaric.footballleaguesapp
//
//import android.graphics.Canvas
//import android.graphics.ColorFilter
//import android.graphics.Picture
//import android.graphics.PixelFormat
//import android.graphics.drawable.Drawable
//import com.caverock.androidsvg.SVG
//
//class SVGDrawable(private val svg: SVG) : Drawable() {
//
//    override fun draw(canvas: Canvas) {
//        // Scale the SVG to fit the canvas
//        val canvasWidth = bounds.width().toFloat()
//        val canvasHeight = bounds.height().toFloat()
//        svg.documentViewBox?.let { viewBox ->
//            val scale = minOf(canvasWidth / viewBox.width(), canvasHeight / viewBox.height())
//            canvas.save()
//            canvas.scale(scale, scale)
//            canvas.translate((canvasWidth - viewBox.width() * scale) / 2f, (canvasHeight - viewBox.height() * scale) / 2f)
//            svg.renderToCanvas(canvas)
//            canvas.restore()
//        }
//    }
//
//    override fun setAlpha(alpha: Int) {}
//
//    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT
//
//    override fun setColorFilter(colorFilter: ColorFilter?) {}
//}
//
