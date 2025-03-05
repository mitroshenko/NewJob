package com.mitroshenko.newjob.presentation.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mitroshenko.newjob.databinding.FragmentBasketBinding


class BasketFragment : Fragment() {

    private var _binding: FragmentBasketBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(BasketViewModel::class.java)

        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textResponses
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}