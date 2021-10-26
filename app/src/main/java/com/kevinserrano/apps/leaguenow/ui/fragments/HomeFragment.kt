package com.kevinserrano.apps.leaguenow.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kevinserrano.apps.leaguenow.LeagueNowApp
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.databinding.FragmentHomeBinding
import com.kevinserrano.apps.leaguenow.domain.models.TeamModel
import com.kevinserrano.apps.leaguenow.presentation.state.State
import com.kevinserrano.apps.leaguenow.presentation.viewModels.SharedViewModel
import com.kevinserrano.apps.leaguenow.presentation.viewModels.HomeViewModel
import com.kevinserrano.apps.leaguenow.ui.activities.HomeActivity
import com.kevinserrano.apps.leaguenow.ui.activities.TeamDetailsActivity
import com.kevinserrano.apps.leaguenow.ui.adapters.FavoritesAdapter
import com.kevinserrano.apps.leaguenow.ui.adapters.TeamsAdapter


/**
 * Created by Kevin Serrano 28/08/21
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by lazy {
        val factory = (requireActivity() as HomeActivity).homeComponent.getTeamsComponentViewModelFactory()
        ViewModelProvider(this,factory).get(HomeViewModel::class.java)
    }
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var teamsAdapter: TeamsAdapter
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMembers()
        setUpViews()
        initObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity = (activity as HomeActivity)
        (activity.application as LeagueNowApp).appComponent.inject(this)
    }


    private fun initMembers() {
        favoritesAdapter = FavoritesAdapter(this::showDetailTeam)
        teamsAdapter = TeamsAdapter(this::showDetailTeam)
    }

    private fun setUpViews() {
        binding.rvFavorites.adapter = favoritesAdapter
        binding.rvTeams.adapter = teamsAdapter
    }

    private fun showLoading(){
        if (!binding.animationView.isAnimating) {
            binding.animationView.visibility = View.VISIBLE
            binding.animationView.playAnimation()
        }
    }

    private fun hideLoading(){
        if (binding.animationView.isAnimating) {
            binding.animationView.visibility = View.GONE
            binding.animationView.pauseAnimation()
        }
    }

    private fun initObservers(){
        sharedViewModel.filterTeams.observe(viewLifecycleOwner,{ leagueId ->
            homeViewModel.fetchTeams(leagueId)
        })
        homeViewModel.stateGetFavorites.observe(viewLifecycleOwner,{
            when (it) {
                is State.Success -> {
                    binding.lbFteams.visibility = View.VISIBLE
                    favoritesAdapter.setFavorites(it.responseTo())
                }
                is State.Empty -> {
                    binding.lbFteams.visibility = View.GONE
                }
                else -> {
                    binding.lbFteams.visibility = View.GONE
                }
            }
        })
        homeViewModel.stateGetTeams.observe(viewLifecycleOwner,{
            when (it) {
                is State.Loading -> {
                    teamsAdapter.deleteAll()
                    showLoading()
                }
                is State.Success -> {
                    hideLoading()
                    teamsAdapter.setTeams(it.responseTo())
                }
                is State.Empty -> {
                    hideLoading()
                    findNavController().navigate(R.id.action_navigation_home_to_emptyFragment)
                }
                else -> {
                    hideLoading()
                    findNavController().navigate(R.id.action_navigation_home_to_navigation_error)
                }
            }
        })
    }

    private fun showDetailTeam(team: TeamModel) {
        TeamDetailsActivity.startActivity(requireContext(),team)
    }


    override fun onResume() {
        if (!binding.animationView.isAnimating) {
            binding.animationView.resumeAnimation()
        }
        super.onResume()
    }

    override fun onStop() {
        hideLoading()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (binding.animationView.isAnimating) {
            binding.animationView.cancelAnimation()
        }
        _binding = null
    }

}