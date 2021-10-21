package com.kevinserrano.apps.leaguenow.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kevinserrano.apps.leaguenow.databinding.FragmentEmptyBinding

/**
 * Created by Kevin Serrano 28/08/21
 */
class EmptyFragment : Fragment() {

    private var _binding: FragmentEmptyBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmptyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        if (!binding.animationView.isAnimating) {
            binding.animationView.resumeAnimation()
        }
        super.onResume()
    }

    override fun onStop() {
        if (binding.animationView.isAnimating) {
            binding.animationView.pauseAnimation()
        }
        super.onStop()
    }

    override fun onDestroyView() {
        if (binding.animationView.isAnimating) {
            binding.animationView.cancelAnimation()
        }
        _binding = null
        super.onDestroyView()
    }

}