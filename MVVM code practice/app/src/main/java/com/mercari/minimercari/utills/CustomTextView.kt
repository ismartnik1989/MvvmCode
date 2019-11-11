package com.mercari.minimercari.utills

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.mercari.minimercari.R


/**
 * Created by Mukesh Kumar on 10/30/2019.
 */
class CustomTextView : AppCompatTextView {

    /**
     * Instantiates a new Custom text view.
     *
     * @param context the context
     */
    constructor(context: Context) : super(context) {}

    /**
     * Instantiates a new Custom text view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        if (isInEditMode) {
            return
        }
        parseAttributes(context, attrs)
    }

    /**
     * Instantiates a new Custom text view.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        if (isInEditMode) {
            return
        }
        parseAttributes(context, attrs)
    }

    private fun parseAttributes(context: Context, attrs: AttributeSet) {
        val values = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)

        //The value 0 is a default, but shouldn't ever be used since the attr is an enum
        val typeface = values.getInt(R.styleable.CustomTextView_typeface, 0)

        when (typeface) {

            Myriad_Pro_Regular -> setTypeface(
                Typeface.createFromAsset(
                    context.assets, "fonts/Myriad_Pro_Regular.ttf"
                )
            )

            Myriad_Pro_Bold -> setTypeface(
                Typeface.createFromAsset(
                    context.assets, "fonts/Myriad_Pro_Bold.ttf"
                )
            )

            New_Myriad_Pro_Regular -> setTypeface(
                Typeface.createFromAsset(
                    context.assets, "fonts/New_MyriadPro_Regular.ttf"
                )
            )

            New_Myriad_Pro_Bold -> setTypeface(
                Typeface.createFromAsset(
                    context.assets, "fonts/New_MyriadPro_Bold.ttf"
                )
            )
        }

        values.recycle()
    }

    companion object {


        private val Myriad_Pro_Regular = 0
        private val Myriad_Pro_Bold = 1
        private val New_Myriad_Pro_Regular = 2
        private val New_Myriad_Pro_Bold = 3
    }


}
