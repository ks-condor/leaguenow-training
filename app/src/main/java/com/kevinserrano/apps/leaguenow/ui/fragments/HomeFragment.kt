package com.kevinserrano.apps.leaguenow.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.databinding.FragmentHomeBinding
import com.kevinserrano.apps.leaguenow.presentation.models.TeamPresentation
import com.kevinserrano.apps.leaguenow.presentation.state.State
import com.kevinserrano.apps.leaguenow.presentation.viewModels.SharedViewModel
import com.kevinserrano.apps.leaguenow.presentation.viewModels.HomeViewModel
import com.kevinserrano.apps.leaguenow.ui.activities.TeamDetailsActivity
import com.kevinserrano.apps.leaguenow.ui.adapters.FavoritesAdapter
import com.kevinserrano.apps.leaguenow.ui.adapters.TeamsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by Kevin Serrano 28/08/21
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
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
        collectUiState()
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

    private fun collectUiState(){
        sharedViewModel.filterTeams.observe(viewLifecycleOwner,{ leagueId ->
            homeViewModel.fetchTeams(leagueId)
        })
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.stateGetFavorites.collect() { state ->
                when (state) {
                    is State.Success -> {
                        binding.lbFteams.visibility = View.VISIBLE
                        favoritesAdapter.setFavorites(state.responseTo())
                    }
                    is State.Empty -> {
                        binding.lbFteams.visibility = View.GONE
                    }
                    else -> {
                        binding.lbFteams.visibility = View.GONE
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.stateGetTeams.collect() { state ->
                when (state) {
                    is State.Loading -> {
                        teamsAdapter.deleteAll()
                        showLoading()
                    }
                    is State.Success -> {
                        hideLoading()
                        teamsAdapter.setTeams(state.responseTo())
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
            }
        }
    }

    private fun showDetailTeam(teamPresentation: TeamPresentation) {
        TeamDetailsActivity.startActivity(requireContext(),teamPresentation)
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