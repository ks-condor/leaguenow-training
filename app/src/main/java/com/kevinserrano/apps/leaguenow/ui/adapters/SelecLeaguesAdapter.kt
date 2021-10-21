package com.kevinserrano.apps.leaguenow.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.presentation.models.LeaguePresentation
import com.kevinserrano.apps.leaguenow.utilities.CallbackT


/**
 * Created by Kevin Serrano 28/08/21
 */
class SelecLeaguesAdapter(private val clickCallback: CallbackT<LeaguePresentation>) :
    RecyclerView.Adapter<SelecLeaguesAdapter.LeagueViewHolder>() {

    private var recyclerViewItems = emptyList<LeaguePresentation>()

    inner class LeagueViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val tvLeague: TextView = itemView.findViewById(R.id.tv_league)
        fun bind(league: LeaguePresentation) = with(itemView) {
            tvLeague.text = league.strLeagueAlternate
            setOnClickListener {
                clickCallback.invoke(league)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): LeagueViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_select_league,
                parent, false)
        return LeagueViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: LeagueViewHolder,
        position: Int,
    ) {
        val item = recyclerViewItems[holder.adapterPosition]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return recyclerViewItems.size
    }

    fun setLeagues(recyclerViewItems: List<LeaguePresentation>) {
        this.recyclerViewItems = recyclerViewItems
        notifyDataSetChanged()
    }

}
