package com.example.sportevents.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportevents.R
import com.example.sportevents.databinding.ItemFixtureBinding
import com.example.sportevents.data.model.Result


class FixtureAdapter(private val onItemClicked: (position: Int) -> Unit) :
    ListAdapter<Result, FixtureAdapter.FixtureViewHolder>(FixturesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        return FixtureViewHolder.create(parent, onItemClicked)
    }

    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class FixtureViewHolder(
        item: View,
        private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(item) {
        private val binding = ItemFixtureBinding.bind(item)

        fun bind(fixture: Result) = with(binding) {
            root.setOnClickListener {
                onItemClicked.invoke(adapterPosition)
            }

            Glide
                .with(binding.root.context)
                .load(fixture.event_home_team_logo)
                .into(homeTeamImg)
            Glide
                .with(binding.root.context)
                .load(fixture.event_away_team_logo)
                .into(awayTeamImg)

            tvScore.text = fixture.event_final_result
            tvTeamHomeName.text = fixture.event_home_team
            tvTeamAwayName.text = fixture.event_away_team
            tvDate.text = fixture.event_date
            tvStatus.text = fixture.event_status
        }

        companion object {
            fun create(
                parent: ViewGroup,
                onItemClicked: (position: Int) -> Unit
            ): FixtureViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_fixture, parent, false)
                return FixtureViewHolder(view, onItemClicked)
            }
        }
    }

    class FixturesComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.event_key == newItem.event_key
        }
    }
}