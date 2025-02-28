package com.example.ca_fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.xy.*
import java.text.DecimalFormat

class BarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bar_fragment, container, false)
        val plot = view.findViewById<XYPlot>(R.id.plot)

        // X-axis values shifted slightly to separate bars at each x-position
        val xVals = listOf(1, 2, 3, 4)
        val xValsA = xVals.map { it - 0.15}  // Shift left
        val xValsB = xVals.map { it }        // Centered
        val xValsC = xVals.map { it }  // Shift right

        // Y-values for each category (a, b, c)
        val seriesA = SimpleXYSeries(xValsA, listOf(4.3,2.5, 3.5, 4.5), "a")
        val seriesB = SimpleXYSeries(xValsB, listOf(2.4, 4.4, 1.8, 2.8), "b")
        val seriesC = SimpleXYSeries(xValsC, listOf(2.0, 2.0, 3.0, 5.0), "c")

        // Format bars
        val formatterA = BarFormatter(Color.BLUE, Color.BLACK)
        val formatterB = BarFormatter(Color.RED, Color.BLACK)
        val formatterC = BarFormatter(Color.GRAY, Color.BLACK)

        // Set plot configurations
        plot.setDomainStep(StepMode.INCREMENT_BY_VAL, 1.0)  // Space out x-axis
        plot.setRangeBoundaries(0, 6, BoundaryMode.FIXED)   // Y-axis from 0 to 6

        // Adjust domain boundaries to give more space to the last bar
        plot.setDomainBoundaries(0f, 5f, BoundaryMode.FIXED)  // x-axis from 0 to 5

        // Add series to plot
        plot.addSeries(seriesA, formatterA)
        plot.addSeries(seriesB, formatterB)
        plot.addSeries(seriesC, formatterC)

        // Ensure x-axis labels are integers (1, 2, 3, 4)
        plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = DecimalFormat("0")

        // Adjust bar width & grouping
        val barRenderer = plot.getRenderer(BarRenderer::class.java)
        barRenderer?.setBarGroupWidth(BarRenderer.BarGroupWidthMode.FIXED_WIDTH, 55f)

        return view
    }
}
