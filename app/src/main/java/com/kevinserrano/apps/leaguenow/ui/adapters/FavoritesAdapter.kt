package com.kevinserrano.apps.leaguenow.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.data.local.Team
import com.kevinserrano.apps.leaguenow.domain.models.TeamModel
import com.kevinserrano.apps.leaguenow.utilities.CallbackT
import com.kevinserrano.apps.leaguenow.utilities.bindImageUrl

/**
 * Created by Kevin Serrano 28/08/21
 */
class FavoritesAdapter(private val callbackDetailTeam: CallbackT<TeamModel>) :
    RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    private var recyclerViewItems = emptyList<TeamModel>()

    inner class FavoriteViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val badge: ImageView = itemView.findViewById(R.id.badge)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val league: TextView = itemView.findViewById(R.id.league)

        fun bind(team: TeamModel) = with(itemView) {
            badge.bindImageUrl(url = team.strTeamBadge, width = 100, height = 100)
            name.text = team.strTeam
            league.text = team.strStadium
            itemView.setOnClickListener {
                callbackDetailTeam.invoke(team)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FavoriteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_favorite_team,
                parent, false
            )
        return FavoriteViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: FavoriteViewHolder,
        position: Int,
    ) {
        val item = recyclerViewItems[holder.adapterPosition]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return recyclerViewItems.size
    }

    fun setFavorites(recyclerViewItems: List<TeamModel>) {
        this.recyclerViewItems = recyclerViewItems
        notifyDataSetChanged()
    }

}
