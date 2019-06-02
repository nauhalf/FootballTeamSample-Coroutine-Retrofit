package com.dicoding.naufal.footballteamsample.data.remote.team

import com.google.gson.annotations.SerializedName


data class TeamResponse(
    @SerializedName("teams")
    var listTeam: List<Team> = emptyList()
) {
    data class Team(
        @SerializedName("idTeam")
        var id: String? = null,
        @SerializedName("strTeam")
        var team: String? = null,
        @SerializedName("strTeamBadge")
        var badge: String? = null
    )
}