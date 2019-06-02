package com.dicoding.naufal.footballteamsample.data.remote

import com.dicoding.naufal.footballteamsample.data.remote.team.TeamResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballTeamServices{

    //suspend nya dihapus (karena masih bug pas make keyword suspend di functionnya)
    @GET("lookup_all_teams.php")
    fun getLookupAllTeams(@Query("id") id: Int = 4328) : Deferred<Response<TeamResponse>>
}