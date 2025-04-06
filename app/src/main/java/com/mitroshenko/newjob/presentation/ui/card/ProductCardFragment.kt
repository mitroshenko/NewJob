package com.mitroshenko.newjob.presentation.ui.card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.adapter.ReviewsAdapter
import com.mitroshenko.newjob.databinding.FragmentProductCardBinding
import com.mitroshenko.newjob.data.model.IdCard.IdCardModel
import com.mitroshenko.newjob.data.repository.basket.Entity
import com.mitroshenko.newjob.data.repository.favourites.FavouriteEntity

class ProductCardFragment : Fragment() {
    private val viewModel: ProductCardViewModel by viewModels {ProductCardViewModelFactory()}
    private val idCardModel: IdCardModel by activityViewModels()
    private val revadapter = ReviewsAdapter()
    private var _binding: FragmentProductCardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductCardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.rcReviews.layoutManager = LinearLayoutManager(context)
        binding.rcReviews.adapter = revadapter
        binding.ibBack.setOnClickListener {
            view?.findNavController()!!
                .navigate(R.id.action_productCardFragment_to_navigation_search)
        }
        viewModel.apply {
            productList.observe(viewLifecycleOwner) { card ->
                binding.apply {
                    tvPrice2.text = card.price.toString()
                    tvTitle.text = card.title
                    tvDescription.text = card.description
                    tvRaiting.text = card.rating.toString()
                    tvBrand.text = card.brand
                    tvPrice1.text = "Price"
                    tvDescriptionTitle.text = "Description"
                    tvReviews.text = "Reviews"
                    Glide.with(requireContext())
                        .load(card.images[0])
                        .into(ivFoto)
                }
            }
            reviewsList.observe(viewLifecycleOwner) {
                revadapter.submitList(it)
            }
        }
        idCardModel.idCard.observe(activity as LifecycleOwner) {
            val idCard = it
            viewModel.apply {
                loadReviews(idCard)
                loadCard(idCard)
                binding.icFav2.setOnClickListener{
                    viewModel.insertFavourite(
                        favouriteEntity = FavouriteEntity(
                            title = binding.tvTitle.text.toString(),
                            brand = binding.tvBrand.text.toString(),
                            rating = binding.tvRaiting.text.toString(),
                            price = binding.tvPrice2.text.toString(),
                            images = binding.ivFoto.toString(),
                            idCardProd = idCard
                        )
                    )
                }
                binding.btnAdd.setOnClickListener{
                    viewModel.insertBasket(
                        prod = Entity(
                            title = binding.tvTitle.text.toString(),
                            brand = binding.tvBrand.text.toString(),
                            rating = binding.tvRaiting.text.toString(),
                            price = binding.tvPrice2.text.toString(),
                            images = binding.ivFoto.toString(),
                            idCardProd = idCard
                        )
                    )
                }
            }
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}