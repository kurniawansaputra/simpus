package id.go.kebumenkab.simpus.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import id.go.kebumenkab.simpus.R

class Dialog {
    private var dialogBuilder: AlertDialog? = null

    fun showProgressDialog(context: Context?) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.layout_loading, null)
        dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(true)
            .create()

        dialogBuilder?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogBuilder?.show()
    }

    fun hideDialog() {
        if (dialogBuilder != null){
            dialogBuilder?.dismiss()
        }
    }
}