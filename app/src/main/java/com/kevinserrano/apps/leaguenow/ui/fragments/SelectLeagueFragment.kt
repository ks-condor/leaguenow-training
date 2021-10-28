package com.kevinserrano.apps.leaguenow.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.databinding.FragmentSelectLeagueBinding
import com.kevinserrano.apps.leaguenow.presentation.models.LeaguePresentation
import com.kevinserrano.apps.leaguenow.ui.adapters.SelecLeaguesAdapter
import com.kevinserrano.apps.leaguenow.utilities.leagues


/**
 * Created by Kevin Serrano 28/08/21
 */

class SelectVideoServerFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSelectLeagueBinding? = null
    private val binding get() = _binding!!

    var onSelectListener: (league: LeaguePresentation) -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentSelectLeagueBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMembers()
    }

    private fun initMembers() {
        val adapter = SelecLeaguesAdapter(this::selectedLeague)
        binding.rvLeagues.adapter = adapter
        adapter.setLeagues(leagues)
    }

    private fun selectedLeague(league: LeaguePresentation) {
        dismiss()
        onSelectListener(league)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

fun FragmentActivity?.showSelectLeagueFragment(onSelectListener: (league: LeaguePresentation) -> Unit) {
    val fragment = SelectVideoServerFragment()
    fragment.onSelectListener = onSelectListener
    this?.let {
        fragment.show(
            it.supportFragmentManager,
            SelectVideoServerFragment::class.java.name
        )
    }
}
