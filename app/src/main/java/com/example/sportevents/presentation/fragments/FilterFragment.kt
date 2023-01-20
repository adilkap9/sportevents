package com.example.sportevents.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.sportevents.R
import com.example.sportevents.databinding.FragmentFilterBinding
import com.example.sportevents.domain.FixtureRepositoryImpl
import com.example.sportevents.presentation.viewmodels.HomeViewModel
import com.example.sportevents.presentation.viewmodels.HomeViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilterBinding
    private var dateFrom: String? = null
    private var dateTo: String? = null

    private val viewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(FixtureRepositoryImpl())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            calendarView.setOnDateChangeListener { _, year, month, day ->

                // Setting first date-point if none of them is set yet
                if (dateFrom == null && dateTo == null) {
                    dateFrom = "$year-${month + 1}-$day"
                    tvFrom.text = getString(R.string.to)
                    Toast.makeText(context, "Now pick ending date", Toast.LENGTH_SHORT).show()
                    return@setOnDateChangeListener
                }
                // Setting the second date-point if the first one is already set applying dates
                if (dateFrom != null && dateTo == null) {
                    dateTo = "$year-${month + 1}-$day"
                    viewModel.setDate(Pair(dateFrom, dateTo))
                    dismiss()
                }
            }
        }
    }
}