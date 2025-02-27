package com.example.ca_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.xy.BarFormatter
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.XYPlot
import com.androidplot.xy.XYSeries
import com.androidplot.xy.XYGraphWidget
import com.androidplot.xy.BoundaryMode
import java.util.Arrays

class BarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bar_fragment, container, false)

        // Find XYPlot in the layout
        val plot = view.findViewById<XYPlot>(R.id.plot)

        // Create data series
        val series = SimpleXYSeries(
            Arrays.asList(1, 2, 3, 4),
            Arrays.asList(4.3, 2.5, 3.5, 4.5),
            "Bar Data"
        )

        // Format and add series
        val formatter = BarFormatter()
        plot.addSeries(series, formatter)

        return view
    }
}
