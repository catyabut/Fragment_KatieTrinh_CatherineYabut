package com.example.ca_fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.SimpleXYSeries
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
            val series = SimpleXYSeries(
                listOf<Number>(1, 2, 3, 4),
                listOf<Number>(4.3, 2.5, 3.5, 4.5),
                "Line Series"
            )

            val seriesFormat = LineAndPointFormatter(Color.RED, Color.BLUE, null, null)
            plot.addSeries(series, seriesFormat)
        } else {
            Log.e("LineFragment", "plot is null!")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }
}
