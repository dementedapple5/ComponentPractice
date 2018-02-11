package com.example.mylabelview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.TextView


class MyLabelView : TextView {
    private lateinit var mText: String
    private lateinit var mPaint: Paint


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

        // Load attributes
        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.MyLabelView, defStyle, 0)

        //Label Text
        val charSequence: CharSequence = styledAttrs.getString(R.styleable.MyLabelView_text)
        if (!charSequence.isEmpty()) setText(charSequence.toString())

        //Label textColor
        val textColor: Int = styledAttrs.getColor(R.styleable.MyLabelView_textColor, Color.RED)
        setTextColor(textColor)

        //Label text size
        val textSize: Int = styledAttrs.getDimensionPixelOffset(R.styleable.MyLabelView_textSize, 24)
        if (textSize > 0) setTextSize(textSize)


        styledAttrs.recycle()

        //Setting brush color and size
        mPaint.textSize = textSize.toFloat()
        mPaint.color = textColor


        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements()
    }

    override fun getText(): String{
        return mText
    }

    fun setText(text: String){
        mText = text
        requestLayout()
        invalidate()
    }

    override fun setTextColor(textColor: Int){
        mPaint.color = textColor
        requestLayout()
        invalidate()
    }

    fun setTextSize(textSize: Int){
        mPaint.textSize = textSize.toFloat()
        requestLayout()
        invalidate()
    }

    private fun invalidateTextPaintAndMeasurements() {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode: Int = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize: Int = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode: Int = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize: Int = MeasureSpec.getSize(heightMeasureSpec)

        var width: Int = (mPaint.measureText(mText) + paddingLeft + paddingRight).toInt()
        var height: Int = ((-mPaint.fontMetrics.ascent + mPaint.fontMetrics.descent) + paddingTop + paddingBottom).toInt()

        when (widthMode) {
            MeasureSpec.EXACTLY -> width = widthSize

            MeasureSpec.AT_MOST -> if (width > widthSize) width = widthSize

            MeasureSpec.UNSPECIFIED -> width = 30
        }

        when (heightMode) {
            MeasureSpec.EXACTLY -> height = heightSize

            MeasureSpec.AT_MOST -> if (height > heightSize) height = heightSize

            MeasureSpec.UNSPECIFIED -> height = 30
        }

        this.setMeasuredDimension(width, height)


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawText(mText, paddingLeft.toFloat(), paddingTop - mPaint.fontMetrics.ascent, mPaint)

    }
}
