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

        if (!::plot.isInitialized) {
            Log.e("BarFragment", "plot is not initialized!")
            return
        }

        // Initialize PixelUtils (required for dpToPix())
        PixelUtils.init(context)

        // Shared X-Values (All bars share the same x-points)
        val xValues = listOf(1, 2, 3, 4)

        // Y-Values for Each Bar
        val yValuesA = listOf(4.3, 2.5, 3.5, 4.5)
        val yValuesB = listOf(2.4, 4.4, 1.8, 1.8)
        val yValuesC = listOf(2.0, 2.0, 3.0, 5.0)

        // Offsets to make bars appear side by side
        val offsetA = 0.01  // Shift A slightly left
        val offsetB = 0.01   // Keep B in center
        val offsetC = 0.01   // Shift C slightly right

        // Adjusted X-Values for each series
        val xValuesA = xValues.map { it + offsetA }
        val xValuesB = xValues.map { it + offsetB }
        val xValuesC = xValues.map { it + offsetC }

        // Creating Bar Series
        val seriesA = SimpleXYSeries(xValuesA, yValuesA, "A")
        val formatA = BarFormatter(Color.RED, Color.BLACK)

        val seriesB = SimpleXYSeries(xValuesB, yValuesB, "B")
        val formatB = BarFormatter(Color.GREEN, Color.BLACK)

        val seriesC = SimpleXYSeries(xValuesC, yValuesC, "C")
        val formatC = BarFormatter(Color.BLUE, Color.BLACK)

        // Adding series to plot
        plot.addSeries(seriesA, formatA)
        plot.addSeries(seriesB, formatB)
        plot.addSeries(seriesC, formatC)

        // Set bar orientation to SIDE_BY_SIDE (prevents stacking)
        val renderer = plot.getRenderer(BarRenderer::class.java)
        renderer?.setBarOrientation(BarRenderer.BarOrientation.SIDE_BY_SIDE)

        // Set the bar group width with a small gap
        renderer?.setBarGroupWidth(
            BarRenderer.BarGroupWidthMode.FIXED_GAP,
            PixelUtils.dpToPix(3F)
        )

        // Adjust X-axis range to fit the bars properly
        plot.setDomainBoundaries(0.5, 4.5, BoundaryMode.FIXED)
        plot.setRangeBoundaries(0, 5.5, BoundaryMode.FIXED)

        // Redraw to apply changes
        plot.redraw()
    }
}
