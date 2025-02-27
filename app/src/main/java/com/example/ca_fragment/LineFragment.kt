package com.example.ca_fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.xy.BoundaryMode
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.StepMode
import com.androidplot.xy.XYPlot
import com.example.ca_fragment.databinding.LineFragmentBinding
import java.util.Arrays

class LineFragment : Fragment() {
    private var _binding: LineFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LineFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Now safely access binding.plot after view is created
        val plot = binding.plot

        // Check if plot is not null before using it

        if (plot != null) {
            // Line A (original)
            val seriesA = SimpleXYSeries(
                listOf<Number>(1, 2, 3, 4), // X-axis
                listOf<Number>(4.3, 2.5, 3.5, 4.5), // Y-axis
                "A"
            )
            val formatA = LineAndPointFormatter(Color.RED, Color.BLUE, null, null)

            // Line B
            val seriesB = SimpleXYSeries(
                listOf<Number>(1, 2, 3, 4),
                listOf<Number>(2.4, 4.4, 1.8, 2.8),
                "B"
            )
            val formatB = LineAndPointFormatter(Color.GREEN, Color.YELLOW, null, null)

            // Line C
            val seriesC = SimpleXYSeries(
                listOf<Number>(1, 2, 3, 4),
                listOf<Number>(2, 2, 3, 5),
                "C"
            )
            val formatC = LineAndPointFormatter(Color.MAGENTA, Color.CYAN, null, null)

            // Add all series to the plot
            plot.addSeries(seriesA, formatA)
            plot.addSeries(seriesB, formatB)
            plot.addSeries(seriesC, formatC)

            // Configure Y-axis range and steps
            plot.setRangeBoundaries(0, 10, BoundaryMode.FIXED) // Y-axis limits
            plot.setRangeStep(StepMode.INCREMENT_BY_VAL, 2.0)  // Steps of 2 (0,2,4,...14)


            plot.setDomainBoundaries(0, 4, BoundaryMode.FIXED) // Set domain (X-axis) from 0 to 4
            plot.setDomainStep(StepMode.INCREMENT_BY_VAL, 1.0) // X-axis step

            // Redraw plot
            plot.redraw()
        } else {
            Log.e("LineFragment", "plot is null!")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }
}
