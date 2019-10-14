package com.example.myapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.appcompat.widget.TintTypedArray
import ru.kpfu.itis.android.news.R

class ChartView @JvmOverloads constructor(context: Context,
                                          attrs: AttributeSet? = null,
                                          defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val paint =
        Paint().apply {
            isAntiAlias = true
            color = Color.YELLOW
            style = Paint.Style.FILL
        }
    private var chartPaddings : Float = dpToPx(CHART_PADDINGS)
    private var paddingX : Float = dpToPx(PADDING_X)
    private var paddingY : Float = dpToPx(PADDING_Y)
    private var columnWidth : Float = dpToPx(WIDTH)
    private var columnHeight : Float = dpToPx(HEIGHT)

    init {
        val array = attrs?.let {
            TintTypedArray.obtainStyledAttributes(context, attrs,
                R.styleable.ChartView, defStyleAttr, defStyleAttr)
        }

        try {
            array?.let {
                chartPaddings = it.getDimension(R.styleable.ChartView_chartPaddings, 200F)
                paddingX = it.getDimension(R.styleable.ChartView_paddingX,100f)
                paddingY = it.getDimension(R.styleable.ChartView_paddingY,100f)
                columnWidth = it.getDimension(R.styleable.ChartView_width, WIDTH + 0F)
                columnHeight= it.getDimension(R.styleable.ChartView_height, HEIGHT + 0F)
            }
        } finally {
            array?.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawRect(canvas)
    }

    private fun drawRect(canvas: Canvas){
        for(i in 0..3){
            val startX = paddingX + chartPaddings * i + columnWidth * i
            val endX = startX + columnWidth
            val endY = paddingY + columnHeight * 3
            val startY = paddingY + columnHeight * i
            val rect = RectF(startX, startY, endX, endY)
            canvas.drawRect(rect, paint)
        }
    }

    private fun dpToPx(dp : Int) : Float =  TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            context.resources.displayMetrics
        )


    companion object{
        private const val CHART_PADDINGS = 72
        private const val PADDING_X = 16
        private const val PADDING_Y = 36
        private const val WIDTH = 50
        private const val HEIGHT = 100
    }
}
