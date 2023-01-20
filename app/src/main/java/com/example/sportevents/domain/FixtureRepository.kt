package com.example.sportevents.domain

import com.example.sportevents.data.model.FixtureModel

interface FixtureRepository {

    suspend fun getData(dateFrom: String, dateTo: String): FixtureModel

}