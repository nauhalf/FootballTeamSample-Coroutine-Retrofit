package com.dicoding.naufal.footballteamsample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dicoding.naufal.footballteamsample.data.remote.team.TeamResponse

class TeamAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var teams = emptyList<TeamResponse.Team>() // Cached copy of team

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamName: TextView = itemView.findViewById(R.id.txt_team_name)
        private val teamBadge: ImageView = itemView.findViewById(R.id.img_team_badge)

        fun bind(team: TeamResponse.Team){
            teamName.text = team.team
            Glide.with(itemView.context)
                .load(team.badge)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(teamBadge)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemView = inflater.inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val current = teams[position]
        holder.bind(current)
    }

    internal fun setTeams(teams: List<TeamResponse.Team>) {
        this.teams = teams
        notifyDataSetChanged()
    }

    override fun getItemCount() = teams.size
}
