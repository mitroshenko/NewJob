package com.mitroshenko.newjob.presentation.ui.profile

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.databinding.FragmentOrderBinding
import com.mitroshenko.newjob.databinding.FragmentProfileBinding
import com.mitroshenko.newjob.presentation.ui.activities.StartActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.apply {
            btnExit.setOnClickListener {
                val intent = Intent(requireContext(), StartActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            btnPromo.setOnClickListener {
                view?.findNavController()!!
                    .navigate(R.id.action_navigation_profile_to_promoFragment)
            }
            btnChat.setOnClickListener {
                view?.findNavController()!!
                    .navigate(R.id.action_navigation_profile_to_navigation_messages)
            }
            btnReviews.setOnClickListener {
                view?.findNavController()!!
                    .navigate(R.id.action_navigation_profile_to_myReviewsFragment)
            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}