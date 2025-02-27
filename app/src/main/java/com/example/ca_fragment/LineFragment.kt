package com.example.ca_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.XYPlot
import com.androidplot.xy.XYSeries
import java.util.Arrays

class LineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_line, container, false)

        // Find the XYPlot in the layout
        val plot = view.findViewById<XYPlot>(R.id.plot)

        // Create data series
        val series = SimpleXYSeries(
            Arrays.asList(1, 2, 3, 4),
            Arrays.asList(2.4, 4.4, 1.8, 2.8),
            "Line Data"
        )

        // Format and add series
        val formatter = LineAndPointFormatter()
        plot.addSeries(series, formatter)

        return view
    }
}