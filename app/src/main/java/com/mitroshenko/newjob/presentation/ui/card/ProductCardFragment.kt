package com.mitroshenko.newjob.presentation.ui.card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.adapter.ReviewsAdapter
import com.mitroshenko.newjob.data.database.ProductsApplications
import com.mitroshenko.newjob.databinding.FragmentProductCardBinding
import com.mitroshenko.newjob.data.model.product.IdCardModel
import com.mitroshenko.newjob.data.repository.Entity

class ProductCardFragment : Fragment() {
    private val idCardModel: IdCardModel by activityViewModels()
    private val revadapter = ReviewsAdapter()
    private var _binding: FragmentProductCardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val productCardViewModel =
            ViewModelProvider(this).get(ProductCardViewModel::class.java).apply {
                ProductCardViewModelFactory((application as ProductsApplications).repository)
                reviewsList.observe(viewLifecycleOwner) {
                    revadapter.submitList(it)
                }
                this.productList.observe(viewLifecycleOwner) { card ->
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
            }
        _binding = FragmentProductCardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.rcReviews.layoutManager = LinearLayoutManager(context)
        binding.rcReviews.adapter = revadapter
        binding.ibBack.setOnClickListener {
            view?.findNavController()!!
                .navigate(R.id.action_productCardFragment_to_navigation_search)
        }
        idCardModel.idCard.observe(activity as LifecycleOwner) {
            val idCard = it
            productCardViewModel.loadReviews(idCard)
            productCardViewModel.loadCard(idCard)
            binding.btnAdd.setOnClickListener{
                productCardViewModel.insert(
                    prod = Entity(
                        title = binding.tvTitle.text.toString(),
                        brand = binding.tvBrand.text.toString(),
                        rating = binding.tvRaiting.text.toString(),
                        price = binding.tvPrice2.text.toString(),
                        images = binding.ivFoto.toString(),
                        idCardProd = idCard
                    ),
                    onFinishCallback = {
                        finish()
                    }
                )
            }
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}