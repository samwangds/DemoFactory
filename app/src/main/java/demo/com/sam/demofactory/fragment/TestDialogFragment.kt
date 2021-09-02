package demo.com.sam.demofactory.fragment

import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager

/**
 *
 * test dismiss leak memory or not
 * @author SamWang
 * @date 2021/8/31
 */
class TestDialogFragment : DialogFragment() {

    override fun show(manager: FragmentManager?, tag: String?) {
        super.show(manager, tag)
    }

}