package com.example.studentoffice.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.studentoffice.R
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.view.*
import org.w3c.dom.Text

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountViewModel =
            ViewModelProviders.of(this).get(AccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_account, container, false)



        val linearLayout: LinearLayout = root.accountLinearLayoutAdmin
        linearLayout.visibility = LinearLayout.GONE

//        val textView: TextView = root.findViewById(R.id.text_account)
//        accountViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }

//    private fun hideElemByUser(root: View) {
//        val typeOfUser :String = "stud"
//        when (typeOfUser){
//            "stud" -> {
//                val btn: Button = root.findViewById(R.id.accountChangeStudentForHeadman)
//                btn.visibility = View.GONE
//                val linearLayout: LinearLayout = root.accountLinearLayoutAdmin
//                linearLayout.visibility = LinearLayout.GONE
//            }
//            "admin" -> {
//                val linearLayout: LinearLayout = root.accountLinearLayoutStud
//                linearLayout.visibility = LinearLayout.GONE
//            }
//            "headman" -> {
//                val linearLayout: LinearLayout = root.accountLinearLayoutAdmin
//                linearLayout.visibility = LinearLayout.GONE
//            }
//        }
//    }
}