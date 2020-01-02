package com.example.studentoffice.ui.groupStatistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.studentoffice.R

class GroupStatisticsFragment : Fragment() {

    private lateinit var groupStatisticsViewModel: GroupStatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupStatisticsViewModel =
            ViewModelProviders.of(this).get(GroupStatisticsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_group_statistic, container, false)
        val textView: TextView = root.findViewById(R.id.text_group_statistic)
        groupStatisticsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}