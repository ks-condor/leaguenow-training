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
class FavoritesAdapter(private val callbackDetailTeamPresentation: CallbackT<TeamPresentation>) :
    RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    private var recyclerViewItems = emptyList<TeamPresentation>()

    inner class FavoriteViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val badge: ImageView = itemView.findViewById(R.id.badge)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val league: TextView = itemView.findViewById(R.id.league)

        fun bind(teamPresentation: TeamPresentation) = with(itemView) {
            badge.bindImageUrl(url = teamPresentation.strTeamBadge, width = 100, height = 100)
            name.text = teamPresentation.strTeam
            league.text = teamPresentation.strStadium
            itemView.setOnClickListener {
                callbackDetailTeamPresentation.invoke(teamPresentation)
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

    fun setFavorites(recyclerViewItems: List<TeamPresentation>) {
        this.recyclerViewItems = recyclerViewItems
        notifyDataSetChanged()
    }

}
