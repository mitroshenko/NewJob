package com.mitroshenko.newjob.presentation.ui.profile

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.btnExit.setOnClickListener{
            val intent = Intent(requireContext(), StartActivity::class.java)
            startActivity(intent)
            activity?.finish()

        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}