package com.tec.frontend

import android.app.AlertDialog
import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.core.content.ContextCompat

class ErrorDialog {
    companion object {
        fun show(context: Context, errorMessage: String) {
            // Create AlertDialog
            val alertDialogBuilder = AlertDialog.Builder(context)

            val titleTextView = TextView(context)
            titleTextView.text = "Error"
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35f)
            titleTextView.setTextColor(
                ContextCompat.getColor(
                    context, R.color.Red
                )
            )
            titleTextView.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
            alertDialogBuilder.setCustomTitle(titleTextView)

            // Create a TextView to set the text size
            val textView = TextView(context)
            textView.text = errorMessage
            textView.setTextSize(
                TypedValue.COMPLEX_UNIT_SP, 35f
            ) // Adjust the text size as needed
            textView.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
            alertDialogBuilder.setView(textView)


            alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}