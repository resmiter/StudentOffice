package com.example.studentoffice.ui.visitRequests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.studentoffice.R

class VisitRequestsFragment : Fragment() {

    private lateinit var visitRequestsViewModel: VisitRequestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        visitRequestsViewModel =
            ViewModelProviders.of(this).get(VisitRequestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_visit_requests, container, false)
        val textView: TextView = root.findViewById(R.id.text_visit_request)
        visitRequestsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}