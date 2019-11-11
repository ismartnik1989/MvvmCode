package com.mercari.minimercari.utills

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mercari.minimercari.R

/**
 * Created by Mukesh Kumar on 10/30/2019.
 */
class CustomProgressDialog {
    private var pd: Dialog? = null

    /**
     * Show dialog.
     *
     * @param context the context
     */
    fun showDialog(context: Context) {
        if (pd == null)
            pd = createProgressDialog(context, "")
        if (!pd!!.isShowing)
            pd!!.show()


    }

    /**
     * Show dialog.
     *
     * @param context the context
     * @param message the message
     */
    fun showDialog(context: Context, message: String) {
        try {
            if (pd == null)
                pd = createProgressDialog(context, message)
            if (!pd!!.isShowing)
                pd!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun createProgressDialog(context: Context, message: String): Dialog {
        val progressDialog = Dialog(context, R.style.progress_dialog)
        progressDialog.setContentView(R.layout.custom_progress_dialog)
        val themeColor = android.R.color.holo_green_dark
        val llMainLayout = progressDialog.findViewById<View>(R.id.llMainLayout) as LinearLayout
        llMainLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        val textView = progressDialog.findViewById<View>(R.id.tv_loading_msg) as TextView
        textView.text = message
        progressDialog.setCancelable(false)
        progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        return progressDialog
    }

    /**
     * Hide dialog.
     */
    fun hideDialog() {
        try {
            if (pd != null && pd!!.isShowing) {
                pd!!.dismiss()

            }
            pd = null
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * Create dialog and show dialog.
     *
     * @param context the context
     * @param message the message
     * @return the dialog
     */
    fun createDialogAndShow(context: Context, message: String): Dialog {
        val progressDialog = this.createProgressDialog(context, message)
        progressDialog.show()
        return progressDialog
    }

    companion object {

        private val TAG = "CustomProgressDialog"
        private var customProgressDialog: CustomProgressDialog? = null

        /**
         * Get instance scm progress dialog.
         *
         * @return the scm progress dialog
         */
        val instance: CustomProgressDialog
            get() {
                if (customProgressDialog == null)
                    customProgressDialog = CustomProgressDialog()
                return customProgressDialog!!
            }


        /**
         * Show progress dialog.
         *
         * @param context the context
         */
        fun showProgressDialog(context: Context) {
            try {
                CustomProgressDialog.instance.showDialog(context, context.getString(R.string.common_loading) + "...")
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        /**
         * Hide progress dialog.
         */
        fun hideProgressDialog() {
            try {
                CustomProgressDialog.instance.hideDialog()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
