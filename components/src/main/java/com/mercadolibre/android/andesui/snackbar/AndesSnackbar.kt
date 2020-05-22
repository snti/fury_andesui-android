package com.mercadolibre.android.andesui.snackbar

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.widget.CardView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.snackbar.action.AndesSnackbarAction
import com.mercadolibre.android.andesui.snackbar.factory.AndesSnackbarAttrs
import com.mercadolibre.android.andesui.snackbar.factory.AndesSnackbarConfiguration
import com.mercadolibre.android.andesui.snackbar.factory.AndesSnackbarConfigurationFactory
import com.mercadolibre.android.andesui.snackbar.type.AndesSnackbarType
import com.mercadolibre.android.andesui.typeface.getFontOrDefault

class AndesSnackbar : CardView {

    /**
     * Getter and setter for [type].
     */
    var type: AndesSnackbarType
        get() = andesSnackbarAttrs.andesSnackbarType
        set(value) {
            andesSnackbarAttrs.andesSnackbarType = value
            setupBackgroundComponents(createConfig())
        }

    /**
     * Getter and setter for [text].
     */
    var text: String?
        get() = andesSnackbarAttrs.andesSnackbarText
        set(value) {
            andesSnackbarAttrs.andesSnackbarText = value
            setupMessageComponent(createConfig())
        }

    /**
     * Getter and setter for [duration].
     */
    var duration: Int
        get() = andesSnackbarAttrs.andesSnackbarDuration
        set(value) {
            andesSnackbarAttrs.andesSnackbarDuration = validateDuration(value)
            setupDurationComponent()
        }





    /**
     * Getter and setter for [action].
     */
    var action: AndesSnackbarAction?
        get() = andesSnackbarAttrs.andesSnackbarAction
        set(value) {
            andesSnackbarAttrs.andesSnackbarAction = value
//            setupTitleComponent(createConfig())
        }





    /**
     * Show the snackbar.
     */
    fun show() {
        if (this::snackbar.isInitialized && !andesSnackbarAttrs.andesSnackbarText.isNullOrEmpty()) {
            snackbar.show()
        }
    }

    /**
     * Dismiss the snackbar.
     */
    fun dismiss() {
        if (this::snackbar.isInitialized && isShown()) {
            snackbar.dismiss()
        }
    }

    /**
     * Returns whether this {@link AndesSnackbar} is currently being shown.
     *
     * @return true if the snackbar is being shown, false otherwise
     */
    override fun isShown(): Boolean {
        if (this::snackbar.isInitialized) {
            return snackbar.isShown
        }
        return false
    }

    private lateinit var andesSnackbarAttrs: AndesSnackbarAttrs
    private lateinit var snackbar: Snackbar
    private lateinit var view: View

    @Suppress("unused")
    private constructor(context: Context) : super(context) {
        throw IllegalStateException(
            "Constructor without parameters in Andes Snackbar is not allowed. You must provide some attributes."
        )
    }

    @Suppress("unused")
    constructor(
        context: Context, view: View, type: AndesSnackbarType, text: String, duration: Int
    ) : super(context) {
        initAttrs(view, type, text, duration, null)
    }

    @Suppress("unused")
    constructor(
        context: Context, view: View, type: AndesSnackbarType, text: String, duration: Int, action: AndesSnackbarAction
    ) : super(context) {
        initAttrs(view, type, text, duration, action)
    }

    private fun initAttrs(
        view: View,
        type: AndesSnackbarType,
        text: String,
        duration: Int,
        action: AndesSnackbarAction? = null
    ) {
        andesSnackbarAttrs = AndesSnackbarAttrs(type, text, validateDuration(duration), action)
        val config = AndesSnackbarConfigurationFactory.create(context, view, andesSnackbarAttrs)
        setupComponents(config)
    }

    /**
     * Responsible for setting up all properties of each component that is part of this badge.
     * Is like a choreographer ;)
     */
    private fun setupComponents(config: AndesSnackbarConfiguration) {
        initComponents(config)
        setupViewId()

        setupBackgroundComponents(config)
        setupMessageComponent(config)


//        setupLeftContent(config)
//        setupRightContent(config)
    }

    /**
     * Creates all the views that are part of this snackbar.
     * After a view is created then a view id is added to it.
     */
    private fun initComponents(config: AndesSnackbarConfiguration) {
        view = config.view
        snackbar = Snackbar.make(view, andesSnackbarAttrs.andesSnackbarText!!, andesSnackbarAttrs.andesSnackbarDuration)
    }

    /**
     * Sets a view id to this badge.
     */
    private fun setupViewId() {
        if (id == NO_ID) { // If this view has no id
            id = View.generateViewId()
        }
    }

    /**
     * Gets data from the config and sets to the backroung component of this snackbar.
     */
    private fun setupBackgroundComponents(config: AndesSnackbarConfiguration) {
        cardElevation = 0f
        radius = context.resources.getDimension(R.dimen.andes_snackbar_radius)
        snackbar.view.setBackgroundColor(Color.TRANSPARENT)

        val andesSnackbar = LayoutInflater.from(context).inflate(R.layout.andes_layout_snackbar, this)
        val snackContainer = andesSnackbar.findViewById<ConstraintLayout>(R.id.snack_constraint)
        snackContainer.setBackgroundColor(config.backgroundColor.colorInt(context))

        val layout = snackbar.view as Snackbar.SnackbarLayout
        layout.removeAllViews()
        layout.addView(andesSnackbar, 0)

        layout.setPadding(config.marginLeft, 0, config.marginRight, config.marginBottom)
    }

    /**
     * Gets data from the config and sets to the message of this snackbar.
     */
    private fun setupMessageComponent(config: AndesSnackbarConfiguration) {
        val layout = snackbar.view as Snackbar.SnackbarLayout
        val title = layout.findViewById<TextView>(R.id.tv_message)
        title.text = andesSnackbarAttrs.andesSnackbarText
        title.typeface = context.getFontOrDefault(R.font.andes_font_regular)
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.andes_snackbar_text))
        title.setTextColor(config.textColor.colorInt(context))
        title.setPadding(
            context.resources.getDimension(R.dimen.andes_snackbar_padding).toInt(),
            context.resources.getDimension(R.dimen.andes_snackbar_padding).toInt(),
            context.resources.getDimension(R.dimen.andes_snackbar_padding).toInt(),
            context.resources.getDimension(R.dimen.andes_snackbar_padding).toInt()
        )
    }

    /**
     * Gets data from the config and sets to the duration of this snackbar.
     */
    private fun setupDurationComponent() {
        if (this::snackbar.isInitialized) {
            snackbar.duration = andesSnackbarAttrs.andesSnackbarDuration
        }
    }

    private fun validateDuration(duration: Int): Int {
        return when {
            duration < MIN_DURATION -> MIN_DURATION
            duration > MAX_DURATION -> MAX_DURATION
            else -> duration
        }
    }

    private fun createConfig() = AndesSnackbarConfigurationFactory.create(context, view, andesSnackbarAttrs)

    companion object {
        const val MIN_DURATION = 3000
        const val MAX_DURATION = 10000
    }

}
