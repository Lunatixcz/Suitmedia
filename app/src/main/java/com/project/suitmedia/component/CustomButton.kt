package com.project.suitmedia.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.project.suitmedia.R

class CustomButton : AppCompatButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var txtColor: Int = 0
    private var buttonBackground: Drawable
    private var customText: String = "Default Text"

    init {
        txtColor = ContextCompat.getColor(context, android.R.color.background_light)
        buttonBackground = ContextCompat.getDrawable(context, R.drawable.bg_button) as Drawable

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        background = buttonBackground
        setTextColor(txtColor)
        textSize = 16f
        gravity = Gravity.CENTER
        text = customText
    }

    fun setCustomText(Text: String) {
        customText = Text
        invalidate()
    }
}