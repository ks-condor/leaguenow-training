package com.kevinserrano.apps.leaguenow.ui.activities

import android.animation.LayoutTransition
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.databinding.ActivityTeamDetailsBinding
import com.kevinserrano.apps.leaguenow.domain.models.TeamModel
import com.kevinserrano.apps.leaguenow.presentation.state.State
import com.kevinserrano.apps.leaguenow.presentation.viewModels.DetailsTeamViewModel
import com.kevinserrano.apps.leaguenow.utilities.bindImageUrl
import com.kevinserrano.apps.leaguenow.utilities.get
import com.kevinserrano.apps.leaguenow.utilities.openWebPage
import com.kevinserrano.apps.leaguenow.utilities.put
import dagger.hilt.android.AndroidEntryPoint

private const val MAX_LINES_COLLAPSED = 7
private const val INITIAL_IS_COLLAPSED = true
/**
 * Created by Kevin Serrano 28/08/21
 */

@AndroidEntryPoint
class TeamDetailsActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context, team: TeamModel) {
            context.startActivity(
                Intent(
                    context,
                    TeamDetailsActivity::class.java
                ).apply {
                    put(team)
                }
            )
        }
    }

    private lateinit var binding: ActivityTeamDetailsBinding

    private val detailsTeamViewModel: DetailsTeamViewModel by viewModels()

    private val team: TeamModel by lazy {
        get(TeamModel::class.java)
    }
    private var isCollapsed = INITIAL_IS_COLLAPSED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
        initObservers()
        detailsTeamViewModel.isFavorite(team.idTeam)
    }


    private fun setUpViews() {
        binding.tvTeamName.text = team.strTeam
        binding.tvDescription.text = team.strDescriptionES
        binding.tvTeamFoundationYear.text = team.intFormedYear
        binding.banner.bindImageUrl(team.strTeamBanner)
        binding.badge.bindImageUrl(team.strTeamBadge)
        binding.tvDescription.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
        applyLayoutTransition()
    }

    private fun initObservers() {
        detailsTeamViewModel.stateIsFavorite.observe(this, {
            val drawableId: Int = if (it) {
                R.drawable.ic_baseline_favorite_24
            } else {
                R.drawable.ic_baseline_favorite_border_24
            }
            binding.btnFabFavorite.setImageDrawable(ContextCompat.getDrawable(this, drawableId))
        })
        detailsTeamViewModel.stateGetTeamEvents.observe(this, {
            when (it) {
                is State.Loading -> {

                }
                is State.Success -> {

                }
                is State.Empty -> {

                }
                else -> {

                }
            }
        })
    }


    //-------------------------------Animations-----------------------------

    private fun validateCollapse() {
        if (isCollapsed) {
            binding.tvDescription.maxLines = Integer.MAX_VALUE
            binding.btnSeeMore.text = getString(R.string.read_less)
        } else {
            binding.tvDescription.maxLines = MAX_LINES_COLLAPSED
            binding.btnSeeMore.text = getString(R.string.read_more)
        }
        isCollapsed = !isCollapsed
    }

    private fun applyLayoutTransition() {
        val transition = LayoutTransition()
        transition.setDuration(300)
        transition.enableTransitionType(LayoutTransition.CHANGING)
        binding.container.layoutTransition = transition
    }

    private val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        if (binding.tvDescription.lineCount > 4) {
            binding.tvDescription.maxLines = MAX_LINES_COLLAPSED
            binding.btnSeeMore.visibility = View.VISIBLE
        }
        removeOnGlobalLayoutListeners()
    }

    private fun removeOnGlobalLayoutListeners() {
        binding.tvDescription.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
    }


    //-------------------------------Listeners-----------------------------

    fun onSeeMore(view: View) {
        validateCollapse()
    }

    fun onFabYoutube(view: View) {
        openWebPage(team.strYoutube)
    }

    fun onFabFacebook(view: View) {
        openWebPage(team.strFacebook)
    }

    fun onFabFavorite(view: View) {
        detailsTeamViewModel.favorite(team)
    }

}