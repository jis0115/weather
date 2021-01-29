package jis.lonepine.weather.presentation.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import jis.lonepine.weather.R

class LoadingDialog @JvmOverloads constructor(context: Context): Dialog(context) {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_loading)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
    }

    override fun show() {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        super.show()
    }
}