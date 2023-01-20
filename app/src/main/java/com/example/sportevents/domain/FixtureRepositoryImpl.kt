package com.example.sportevents.domain

import com.example.sportevents.data.ApiFactory

class FixtureRepositoryImpl: FixtureRepository {

    private val retrofit = ApiFactory.messageApi

    override suspend fun getData(dateFrom: String, dateTo: String) = retrofit.getData(from=dateFrom, to=dateTo)
}