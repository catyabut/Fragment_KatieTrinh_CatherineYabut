package com.example.ca_fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androidplot.util.PixelUtils
import com.androidplot.xy.*


class BarFragment : Fragment() {

    private lateinit var plot: XYPlot

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.bar_fragment, container, false)
        plot = rootView.findViewById(R.id.plot) // Find the plot in XML
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val renderer = plot.getRenderer(BarRenderer::class.java)

        if (::plot.isInitialized) {
            val xValues = listOf(1, 2, 3, 4)
            val xOffset = 0.2  // Small offset for side-by-side effect

            // A Bars (Left)
            val seriesA: XYSeries = SimpleXYSeries(
                xValues.map { it - xOffset }, // Shifted left
                listOf(4.3, 2.5, 3.5, 4.5),
                "A"
            )
            val formatA = BarFormatter(Color.RED, Color.BLACK)

            // B Bars (Center)
            val seriesB: XYSeries = SimpleXYSeries(
                xValues, // No shift
                listOf(2.4, 4.4, 1.8, 2.8),
                "B"
            )
            val formatB = BarFormatter(Color.GREEN, Color.BLACK)

            // C Bars (Right)
            val seriesC: XYSeries = SimpleXYSeries(
                xValues.map { it + xOffset }, // Shifted right
                listOf(2, 2, 3, 5),
                "C"
            )
            val formatC = BarFormatter(Color.BLUE, Color.BLACK)

            // Add series to plot
            plot.addSeries(seriesA, formatA)
            plot.addSeries(seriesB, formatB)
            plot.addSeries(seriesC, formatC)

            // Set bar orientation to SIDE_BY_SIDE (This prevents stacking)
            renderer?.setBarOrientation(BarRenderer.BarOrientation.SIDE_BY_SIDE)

            // Set the bar group width with fixed gap
            renderer?.setBarGroupWidth(
                BarRenderer.BarGroupWidthMode.FIXED_GAP,
                PixelUtils.dpToPix(5.0F)
            )

            // Redraw to apply changes
            plot.redraw()
        } else {
            Log.e("BarFragment", "plot is not initialized!")
        }
    }

}
