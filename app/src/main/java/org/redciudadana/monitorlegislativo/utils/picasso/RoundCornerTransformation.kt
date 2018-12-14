package org.redciudadana.monitorlegislativo.utils.picasso

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import com.squareup.picasso.Transformation

class RoundCornerTransformation(val resources: Resources): Transformation {
    override fun key(): String = "circle"

    override fun transform(source: Bitmap?): Bitmap {
        val factory = RoundedBitmapDrawableFactory.create(resources, source)
        factory.cornerRadius = 10f

        val bitmap = Bitmap.createBitmap(factory.intrinsicWidth, factory.intrinsicHeight, source!!.config)
        val canvas = Canvas(bitmap)
        factory.setBounds(0, 0, factory.intrinsicWidth, factory.intrinsicHeight)
        factory.draw(canvas)
        source.recycle()
        return bitmap
    }
}