package com.mitroshenko.newjob.presentation.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.adapter.ProductAdapter
import com.mitroshenko.newjob.databinding.FragmentBasketBinding
import com.mitroshenko.newjob.databinding.FragmentSearchBinding


class BasketFragment : Fragment() {

    private val adapter: ProductAdapter by lazy {
        ProductAdapter { product ->
//            idCardModel.idCard.value = product.id
            view?.findNavController()!!
                .navigate(R.id.action_navigation_search_to_productCardFragment)
        }
    }
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

        binding.rcBasket.layoutManager = LinearLayoutManager(context)
        binding.rcBasket.adapter = adapter
        binding.rcBasket.setOnClickListener {
            view?.findNavController()!!
                .navigate(R.id.action_navigation_search_to_productCardFragment)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}