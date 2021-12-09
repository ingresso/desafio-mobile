package com.jeanbarrossilva.ingresso.ui.view

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.jeanbarrossilva.ingresso.extensions.context.colorOf
import com.jeanbarrossilva.ingresso.extensions.context.withStyledAttributes
import com.jeanbarrossilva.ingresso.extensions.view.dp
import com.jeanbarrossilva.ingresso.ui.R
import com.jeanbarrossilva.ingresso.ui.core.view.IngressoLinearLayout
import com.jeanbarrossilva.ingresso.ui.core.view.SingleChildLayout

class MovieDetailsSectionView: SingleChildLayout {
    private lateinit var childLayout: IngressoLinearLayout
    private lateinit var titleView: TextView
    private lateinit var descriptionView: TextView

    var title = ""
        set(title) {
            field = title
            // Workaround for making the text bold, since using `setTypeface(typeface, Typeface.BOLD)` didn't appear to work.
            titleView.text = Html.fromHtml("<b>$title</b>", Html.FROM_HTML_MODE_LEGACY)
        }
    var description = ""
        set(description) {
            field = description
            descriptionView.text = description
        }

    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr)
    }

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int = 0) {
        assignViews()
        getAttrs(attrs, defStyleAttr)
        addViews()
    }

    /** Gets the attributes that were set in XML and sets them to their according field. **/
    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        context.withStyledAttributes(R.styleable.MovieDetailsSectionView, attrs, defStyleAttr) { index ->
            when (index) {
                R.styleable.MovieDetailsSectionView_title -> title = getString(index).orEmpty()
                R.styleable.MovieDetailsSectionView_description -> description = getString(index).orEmpty()
            }
        }
    }

    /** Assigns the corresponding values to the [View]s of this layout. **/
    private fun assignViews() {
        childLayout = IngressoLinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            spacing = 4.dp()
        }
        titleView = TextView(context).apply {
            setTextAppearance(R.style.TextAppearance_MaterialComponents_Body1)
        }
        descriptionView = TextView(context).apply {
            setTextColor(context.colorOf(android.R.attr.textColorSecondary))
        }
    }

    /** Adds all the [View]s meant to be in this layout. **/
    private fun addViews() {
        addView(childLayout)
        childLayout.addView(titleView)
        childLayout.addView(descriptionView)
    }
}