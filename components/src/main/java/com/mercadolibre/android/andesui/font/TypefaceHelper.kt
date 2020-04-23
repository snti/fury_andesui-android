package com.mercadolibre.android.andesui.font

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.widget.TextView

/**
 * This class is used as a wrapper for our custom font.
 * If you code create a View that supports typeface you should call one of this methods.
 */
object TypefaceHelper {

    private lateinit var typefaceSetter: TypefaceSetter

    /**
     * Attach a typeface setter to this helper class
     * @param typefaceSetter field
     */
    @JvmStatic
    fun attachTypefaceSetter(typefaceSetter: TypefaceSetter) {
        TypefaceHelper.typefaceSetter = typefaceSetter
    }

    /**
     * Sets the typeface to the given [T]
     * @param <T>   A generic for the textview
     * @param view  The view to which apply the font
     * @param font  The [Font] the text should have
    </T> */
    @JvmStatic
    fun <T : TextView?> setTypeface(view: T, font: Font) {
        if (this::typefaceSetter.isInitialized) {
            typefaceSetter.setTypeface(view, font)
        } else {
            // Do log
        }
    }

    /**
     * Sets the typeface to the given [Paint]
     *
     * @param context A context to obtain the font
     * @param paint   The paint to which apply the font
     * @param font    The [Font] the text should have
     */
    @JvmStatic
    fun setTypeface(context: Context, paint: Paint, font: Font) {
        if (this::typefaceSetter.isInitialized) {
            typefaceSetter.setTypeface(context, paint, font)
        } else {
            // Do log
        }
    }

    /**
     * Get a typeface associated to the font passed.
     *
     * @param context to use
     * @param font to use
     * @return associated typeface
     */
    @JvmStatic
    fun getFontTypeface(context: Context, font: Font): Typeface? {
        return if (this::typefaceSetter.isInitialized) {
            typefaceSetter.getTypeface(context, font)
        } else {
            null
        }
    }

    /**
     * Setter for typeface
     */
    interface TypefaceSetter {
        /**
         * Set a typeface to a view
         * @param view to set the typeface to
         * @param font to set
         * @param <T> extends TextView
        </T> */
        fun <T : TextView?> setTypeface(view: T, font: Font)

        /**
         * Set a typeface to the paint
         * @param context to use
         * @param paint to set the typeface
         * @param font to set
         */
        fun setTypeface(context: Context, paint: Paint, font: Font)

        /**
         * Return the typeface associated with the font
         * @param context to use
         * @param font to find the typeface
         * @return typeface associated
         */
        fun getTypeface(context: Context, font: Font): Typeface?
    }

}