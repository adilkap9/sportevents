package com.example.sportevents.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sportevents.R
import com.example.sportevents.databinding.FragmentHomeBinding
import com.example.sportevents.domain.FixtureRepositoryImpl
import com.example.sportevents.presentation.adapters.FixtureAdapter
import com.example.sportevents.presentation.adapters.MarginItemDecoration
import com.example.sportevents.presentation.viewmodels.HomeViewModel
import com.example.sportevents.presentation.viewmodels.HomeViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController

    private val viewModel:HomeViewModel by activityViewModels {
        HomeViewModelFactory(FixtureRepositoryImpl())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FixtureAdapter { position -> onListItemClick(position) }
        navController = Navigation.findNavController(binding.root)



        with (binding) {
            recyclerView.adapter = adapter

            recyclerView.addItemDecoration(
                MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.itemMargin))
            )

            btnOneSignal.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_webviewFragment)
            }

            btnFilter.setOnClickListener {
                navController.navigate(R.id.action_homeFragment_to_filterFragment)
            }
        }

        viewModel.fixtureModelLiveData.observe(viewLifecycleOwner) { messages ->
            messages?.let { items ->
                adapter.submitList(items)
            }}

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            with (binding) {
                recyclerView.isVisible = !isLoading
                progressBar.isVisible = isLoading
            }
        }

    }

    private fun onListItemClick(position: Int) {
        viewModel.setPosition(position)
        navController.navigate(R.id.action_homeFragment_to_fixtureFragment)
    }
}