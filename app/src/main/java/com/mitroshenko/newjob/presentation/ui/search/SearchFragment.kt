package com.mitroshenko.newjob.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.adapter.ProductAdapter
import com.mitroshenko.newjob.databinding.FragmentSearchBinding
import com.mitroshenko.newjob.data.model.product.IdCardModel


class SearchFragment : Fragment() {
    private val idCardModel : IdCardModel by activityViewModels()
    private val adapter: ProductAdapter by lazy {
        ProductAdapter { product ->
            idCardModel.idCard.value = product.id
            view?.findNavController()!!
                .navigate(R.id.action_navigation_search_to_productCardFragment)
        }
    }
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java).apply {
            loadProducts()
            productList.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rcRecommendations.layoutManager = GridLayoutManager(context, 2)
        binding.rcRecommendations.adapter = adapter
        binding.rcRecommendations.setOnClickListener {
            view?.findNavController()!!
                .navigate(R.id.action_navigation_search_to_productCardFragment)
        }

        binding.svSearch.setOnQueryTextListener(searchViewModel.queryTextListener)
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}