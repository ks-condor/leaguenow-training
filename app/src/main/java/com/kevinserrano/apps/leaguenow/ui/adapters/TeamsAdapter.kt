package com.kevinserrano.apps.leaguenow.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.presentation.models.TeamPresentation
import com.kevinserrano.apps.leaguenow.utilities.CallbackT
import com.kevinserrano.apps.leaguenow.utilities.bindImageUrl

/**
 * Created by Kevin Serrano 28/08/21
 */
class TeamsAdapter(private val callbackDetailTeamPresentation: CallbackT<TeamPresentation>) :
    RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    private var recyclerViewItems = emptyList<TeamPresentation>()

    inner class TeamViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val badge: ImageView = itemView.findViewById(R.id.badge)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val stadium: TextView = itemView.findViewById(R.id.stadium)

        fun bind(teamPresentation: TeamPresentation) = with(itemView) {
            badge.bindImageUrl(url = teamPresentation.strTeamBadge, width = 100, height = 100)
            name.text = teamPresentation.strTeam
            stadium.text = teamPresentation.strStadium
            itemView.setOnClickListener {
                callbackDetailTeamPresentation.invoke(teamPresentation)
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

    fun setTeams(recyclerViewItems: List<TeamPresentation>) {
        this.recyclerViewItems = recyclerViewItems
        notifyDataSetChanged()
    }

}
