package com.mitroshenko.newjob.presentation.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.adapter.BasketAdapter
import com.mitroshenko.newjob.data.model.IdCard.IdCardModel
import com.mitroshenko.newjob.databinding.FragmentBasketBinding


class BasketFragment : Fragment() {
    private val viewModel: BasketViewModel by viewModels { BasketViewModelFactory() }
    private val idCardModel: IdCardModel by activityViewModels()
    private val adapter: BasketAdapter by lazy {
        BasketAdapter { entity ->
            idCardModel.idCard.value = entity.idCardProd
            view?.findNavController()!!
                .navigate(R.id.action_navigation_responses_to_productCardFragment)
        }
    }
    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.apply {
            rcBasket.layoutManager = LinearLayoutManager(context)
            rcBasket.adapter = adapter
            rcBasket.setOnClickListener {
                view?.findNavController()!!
                    .navigate(R.id.action_navigation_search_to_productCardFragment)
            }
            btnArrange.setOnClickListener{
                view?.findNavController()!!
                    .navigate(R.id.action_navigation_basket_to_orderFragment)
            }
        }
        viewModel.allProducts.observe(viewLifecycleOwner) { prod ->
            prod.let { adapter.submitList(it) }
        }
        viewModel.apply {
            val simpleCallback =
                object : ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return true
                    }
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.adapterPosition
                        when (direction) {
                            ItemTouchHelper.RIGHT -> {
                                delete(position)
                            }
                            ItemTouchHelper.LEFT -> {
                                delete(position)
                            }
                        }
                    }
                }
            val itemTouchHelper = ItemTouchHelper(simpleCallback)
            itemTouchHelper.attachToRecyclerView(binding.rcBasket)
            allProducts.observe(viewLifecycleOwner) { prod ->
                prod.let { adapter.submitList(it) }
            }
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}