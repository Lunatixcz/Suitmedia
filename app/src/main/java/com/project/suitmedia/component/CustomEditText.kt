package com.project.suitmedia.component

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.project.suitmedia.R

class CustomEditText : AppCompatEditText {

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private var clearButton: Drawable? = null

    private fun init(context: Context) {
        background = ContextCompat.getDrawable(context, R.drawable.bg_edit_text)
        setPadding(72, 48, 72, 48)
        setTextColor(Color.BLACK)
        textSize = 16f
        setHint("Default Text")

        gravity = Gravity.CENTER_VERTICAL

        clearButton = ContextCompat.getDrawable(context, R.drawable.baseline_close_24)?.apply {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        }

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setCompoundDrawables(null, null, if (s?.isNotEmpty() == true) clearButton else null, null)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isEmpty() != true) setTextColor(Color.BLACK)
            }
        })
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP && clearButton != null) {
            if (event.x >= (width - paddingEnd - clearButton!!.intrinsicWidth)) {
                text?.clear()
                return true
            }
        }
        return super.onTouchEvent(event)
    }
}