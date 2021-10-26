package com.kevinserrano.apps.leaguenow.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.kevinserrano.apps.leaguenow.LeagueNowApp
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.databinding.ActivityHomeBinding
import com.kevinserrano.apps.leaguenow.di.HomeComponent
import com.kevinserrano.apps.leaguenow.presentation.viewModels.SharedViewModel
import com.kevinserrano.apps.leaguenow.presentation.viewModels.SharedViewModelFactory
import com.kevinserrano.apps.leaguenow.ui.fragments.showSelectLeagueFragment

/**
 * Created by Kevin Serrano 28/08/21
 */
class HomeActivity : AppCompatActivity() {

    companion object{
        fun startActivity(context: Context){
            context.startActivity(
                Intent(context,
                HomeActivity::class.java))
        }
    }

    private val sharedViewModel: SharedViewModel
        get() =
            ViewModelProvider(this, SharedViewModelFactory.getInstance()).get(SharedViewModel::class.java)

    private lateinit var binding: ActivityHomeBinding
    lateinit var homeComponent: HomeComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        homeComponent = (application as LeagueNowApp).appComponent.getTeamsComponent().create()
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedViewModel.filterTeams("4335")
        binding.btnRetry.text = getString(R.string.title_selected_league_s,
            "Spanish La Liga")
    }

    fun onSelectLeague(view: android.view.View) {
        showSelectLeagueFragment {
            sharedViewModel.filterTeams(it.idLeague)
            binding.btnRetry.text = getString(R.string.title_selected_league_s,
                it.strLeagueAlternate)
        }
    }

}