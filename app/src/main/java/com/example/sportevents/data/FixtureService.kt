package com.example.sportevents.data

import com.example.sportevents.data.Constants.Companion.API_KEY
import com.example.sportevents.data.model.FixtureModel
import retrofit2.http.GET
import retrofit2.http.Query

interface FixtureService {

    @GET("basketball")
    suspend fun getData(
        @Query("met") met: String = "Fixtures",
        @Query("APIkey") apiKey: String = API_KEY,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("leagueId") leagueId: Int = 766
    ): FixtureModel
}