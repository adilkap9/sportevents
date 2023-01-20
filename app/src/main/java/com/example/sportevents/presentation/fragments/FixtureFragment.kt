package com.example.sportevents.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.sportevents.databinding.FragmentFixtureBinding
import com.example.sportevents.domain.FixtureRepositoryImpl
import com.example.sportevents.presentation.viewmodels.HomeViewModel
import com.example.sportevents.presentation.viewmodels.HomeViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FixtureFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFixtureBinding

    private val viewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(FixtureRepositoryImpl())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFixtureBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = viewModel.position.value
        val fixture = viewModel.fixtureModelLiveData.value?.get(position?:0)

        with (binding) {

            Glide
                .with(binding.root.context)
                .load(fixture?.event_home_team_logo)
                .into(homeTeamImg)
            Glide
                .with(binding.root.context)
                .load(fixture?.event_away_team_logo)
                .into(awayTeamImg)

            tvScore.text = fixture?.event_final_result
            tvTeamHomeName.text = fixture?.event_home_team
            tvTeamAwayName.text = fixture?.event_away_team
            tvDate.text = fixture?.event_date
            tvTitle.text = fixture?.event_status
        }

    }
}