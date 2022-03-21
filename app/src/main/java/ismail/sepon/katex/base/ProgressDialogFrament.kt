package ismail.sepon.katex.base

import androidx.fragment.app.DialogFragment

import android.app.Dialog
import android.app.ProgressDialog

import android.os.Bundle





/**
 * Created by MD ISMAIL HOSSAIN SEPON on 14-Aug-21.
 * ismailhossainsepon@gmail.com
 */
class ProgressDialogFrament : DialogFragment() {

    fun newInstance(): ProgressDialogFrament? {
        return ProgressDialogFrament()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = ProgressDialog(activity)
        dialog.setMessage("Loading..")
        dialog.setIndeterminate(true)
        dialog.setCancelable(false)

        // Disable the back button
//        val keyListener: OnKeyListener = object : OnKeyListener() {
//            fun onKey(
//                dialog: DialogInterface?, keyCode: Int,
//                event: KeyEvent?
//            ): Boolean {
//                return if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    true
//                } else false
//            }
//        }
     //   dialog.setOnKeyListener(keyListener)
        return dialog
    }

}