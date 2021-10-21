package com.kevinserrano.apps.leaguenow.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.domain.models.TeamModel
import com.kevinserrano.apps.leaguenow.utilities.CallbackT
import com.kevinserrano.apps.leaguenow.utilities.bindImageUrl

/**
 * Created by Kevin Serrano 28/08/21
 */
class TeamsAdapter(private val callbackDetailTeam: CallbackT<TeamModel>) :
    RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    private var recyclerViewItems = emptyList<TeamModel>()

    inner class TeamViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val badge: ImageView = itemView.findViewById(R.id.badge)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val stadium: TextView = itemView.findViewById(R.id.stadium)

        fun bind(team: TeamModel) = with(itemView) {
            badge.bindImageUrl(url = team.strTeamBadge, width = 100, height = 100)
            name.text = team.strTeam
            stadium.text = team.strStadium
            itemView.setOnClickListener {
                callbackDetailTeam.invoke(team)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TeamViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_team,
                parent, false
            )
        return TeamViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: TeamViewHolder,
        position: Int,
    ) {
        val item = recyclerViewItems[holder.adapterPosition]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return recyclerViewItems.size
    }

    fun deleteAll(){
        this.recyclerViewItems = emptyList()
        notifyDataSetChanged()
    }

    fun setTeams(recyclerViewItems: List<TeamModel>) {
        this.recyclerViewItems = recyclerViewItems
        notifyDataSetChanged()
    }

}
