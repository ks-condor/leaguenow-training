package com.kevinserrano.apps.leaguenow.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kevinserrano.apps.leaguenow.R
import com.kevinserrano.apps.leaguenow.databinding.FragmentErrorBinding
import com.kevinserrano.apps.leaguenow.presentation.viewModels.SharedViewModel

/**
 * Created by Kevin Serrano 28/08/21
 */
class ErrorFragment : Fragment() {

    private var _binding: FragmentErrorBinding? = null

    private val binding get() = _binding!!

    private val description:String by lazy {
        arguments?.getString("errorMessage") ?: ""
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSubTitle.text = description
        binding.btnRetry.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_error_to_navigation_home)
        }
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