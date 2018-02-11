package com.example.mysearchbar

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.my_search_bar.view.*


class MySearchBar : LinearLayout, View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0){
            mBtnDelete -> mEtSearch.setText("")

            mBtnSearch -> mCallback?.onSearchButtonClickedListener(this,mEtSearch.text.toString())
        }
    }

    private var mCallback: OnSearchButtonClickedListener? = null

    interface OnSearchButtonClickedListener {
        fun onSearchButtonClickedListener (source: MySearchBar, currentText: String)
    }

    fun setOnSearchButtonClickedListener(listener: OnSearchButtonClickedListener ){
        mCallback = listener
    }

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
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.my_search_bar, this)

        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MySearchBar, defStyle, 0)

        val charSequence: CharSequence = typedArray.getString(R.styleable.MySearchBar_hintText)
        if (!charSequence.isEmpty()) setHintText(charSequence.toString())

        val textSize = typedArray.getDimensionPixelOffset(R.styleable.MySearchBar_textSize, 18)
        if (textSize > 0) setTextSize(textSize)

        typedArray.recycle()

        mBtnSearch.setOnClickListener(this)
        mBtnDelete.setOnClickListener(this)
    }

    fun setHintText(text: String){
        mEtSearch.setHint(text)
        invalidate()
        requestLayout()
    }

    fun setTextSize(textSize: Int){
        mEtSearch.textSize = textSize.toFloat()
        invalidate()
        requestLayout()
    }
}
