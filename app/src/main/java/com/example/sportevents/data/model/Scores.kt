package com.example.sportevents.data.model

data class Scores(
    val `1stQuarter`: List<StQuarter>,
    val `2ndQuarter`: List<NdQuarter>,
    val `3rdQuarter`: List<RdQuarter>,
    val `4thQuarter`: List<ThQuarter>
)