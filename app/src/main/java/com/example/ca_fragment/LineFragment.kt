package com.example.ca_fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androidplot.xy.*
import kotlinx.android.synthetic.main.fragment_graph.*
import java.util.*

class LineFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_graph, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seriesA = listOf(1, 4, 3, 2)
        val seriesB = listOf(3, 2, 5, 4)
        val seriesC = listOf(2, 3, 2, 5)

        val series1 = SimpleXYSeries(seriesA, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "A")
        val series2 = SimpleXYSeries(seriesB, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "B")
        val series3 = SimpleXYSeries(seriesC, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "C")

        val format1 = LineAndPointFormatter(Color.RED, Color.RED, null, null)
        val format2 = LineAndPointFormatter(Color.BLUE, Color.BLUE, null, null)
        val format3 = LineAndPointFormatter(Color.GREEN, Color.GREEN, null, null)

        plot.addSeries(series1, format1)
        plot.addSeries(series2, format2)
        plot.addSeries(series3, format3)
    }
}
