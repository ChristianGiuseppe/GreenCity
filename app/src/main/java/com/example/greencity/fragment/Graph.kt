package com.example.greencity.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.greencity.R
import com.example.greencity.activity.ColumnChart_Activity
import com.example.greencity.activity.Pie_Activity

class Graph : Fragment() {
    private var textApriCakeChart: TextView? = null
    private var columnApriCakeChart: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_graph, container, false)
        textApriCakeChart = v.findViewById(R.id.cakeChartText)
        columnApriCakeChart = v.findViewById(R.id.columnChartText)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textApriCakeChart?.setOnClickListener {
            val iChart = Intent(context, Pie_Activity::class.java)
            iChart?.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(iChart)
        }

        columnApriCakeChart?.setOnClickListener {
            val iChart = Intent(context, ColumnChart_Activity::class.java)
            iChart?.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(iChart)
        }
    }

}
