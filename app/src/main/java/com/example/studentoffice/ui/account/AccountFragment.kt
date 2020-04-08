package com.example.studentoffice.ui.account

import android.app.FragmentTransaction
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.example.studentoffice.R
import com.example.studentoffice.ui.account.tabs.ChangeStudentFragment
import kotlinx.android.synthetic.main.fragment_account.view.*


class AccountFragment : Fragment(R.layout.fragment_account) {

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountViewModel =
            ViewModelProviders.of(this).get(AccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_account, container, false)
        hideElemByUser(root)
        val button: Button = root.buttonAccountChangeStudentForHeadman
        button.setOnClickListener {
            swapFragment()
        }
        return root
    }

    private fun swapFragment() {
        val newFragment = ChangeStudentFragment()
        val fragManager: FragmentManager? = parentFragmentManager
        val fragmentTransaction: androidx.fragment.app.FragmentTransaction = fragManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, newFragment, "this")
        fragmentTransaction.addToBackStack(null)
        activity!!.onBackPressed()
        fragmentTransaction.commit()
    }

    private fun hideElemByUser(root: View) {
//        User.getUser().type
        when ("headman") {
            "student" -> {
                val btn: Button = root.findViewById(R.id.buttonAccountChangeStudentForHeadman)
                btn.visibility = View.GONE
                val linearLayout: LinearLayout = root.accountLinearLayoutAdmin
                linearLayout.visibility = LinearLayout.GONE
            }
            "admin" -> {
                val linearLayout: LinearLayout = root.accountLinearLayoutHeadman
                linearLayout.visibility = LinearLayout.GONE
            }
            "headman" -> {
                val linearLayout: LinearLayout = root.accountLinearLayoutAdmin
                linearLayout.visibility = LinearLayout.GONE
            }
        }
    }
}