package com.nexcel.nexcellivescore

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import timber.log.Timber
import java.lang.Exception
import com.nexcel.nexcellivescore.Constant.Companion.DEFAULT_TEXT_FONT

class NexcelTextView(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int
) : TextView(context, attributeSet, defStyleAttr) {
    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    private var fontName: String = DEFAULT_TEXT_FONT

    init {
        attributeSet?.run {
            val typedArray =
                context.obtainStyledAttributes(this, R.styleable.NexcelTextView, defStyleAttr, 0)

            try {
                fontName = typedArray.getString(R.styleable.NexcelTextView_nexcelFont)
                    ?: DEFAULT_TEXT_FONT
                val nexcelFont = Typeface.createFromAsset(context.resources.assets, fontName)
                typeface = nexcelFont
            } catch (exception: Exception) {
                Timber.e("Failed to obtain font with error: $exception")
                fontName = DEFAULT_TEXT_FONT
            } finally {
                typedArray.recycle()
            }
        }
    }
}