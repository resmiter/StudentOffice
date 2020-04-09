package com.example.studentoffice.ui.state.main.requestsToAdmin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.studentoffice.R

class RequestsToAdminFragment : Fragment() {

    private lateinit var requestsToAdminViewModel: RequestsToAdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requestsToAdminViewModel =
            ViewModelProviders.of(this).get(RequestsToAdminViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_requests_to_admin, container, false)
        val textView: TextView = root.findViewById(R.id.text_requests_to_admin)
        requestsToAdminViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}