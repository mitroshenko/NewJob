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
import com.mitroshenko.newjob.data.api.ProductApi
import com.mitroshenko.newjob.databinding.FragmentProductCardBinding
import com.mitroshenko.newjob.data.api.model.IdCardModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductCardFragment : Fragment() {
    private val idCardModel: IdCardModel by activityViewModels()
    private val revadapter = ReviewsAdapter()
    private var _binding: FragmentProductCardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val productCardViewModel = ViewModelProvider(this).get(ProductCardViewModel::class.java)

        productCardViewModel.reviewsList.observe(viewLifecycleOwner, {
            revadapter.submitList(it)
        })
        productCardViewModel.productList.observe(viewLifecycleOwner, {
            val card = it.first()
            binding.apply {
                tvPrice2.text = card.price.toString()
                tvTitle.text = card.title
                tvDescription.text = card.description
                tvRaiting.text = card.rating.toString()
                tvBrand.text = card.brand
                tvPrice1.text = "Price"
                tvDescriptionTitle.text = "Description"
                tvReviews.text = "Reviews"
            }
        })

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
//            productCardViewModel.loadCard(idCard)
//            val client = OkHttpClient.Builder()
//            .build()
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://dummyjson.com")
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create()).build()
//        val sf_Api = retrofit.create(ProductApi::class.java)
//        CoroutineScope(Dispatchers.IO).launch {
//            val model = sf_Api.getReviewsById(idCard)
//            activity?.runOnUiThread {
//                binding.apply {
//                    tvPrice2.text = model.price.toString()
//                    tvTitle.text = model.title
//                    tvDescription.text = model.description
//                    tvRaiting.text = model.rating.toString()
//                    tvBrand.text = model.brand
//                    tvPrice1.text = "Price"
//                    tvDescriptionTitle.text = "Description"
//                    tvReviews.text = "Reviews"
//                }
//            }
//        }
        }

//        val url = "https://co14tula-r71.gosuslugi.ru/netcat_files/45/255/385289_maslenica_dlya_detey_6_7_let_27.jpg"
//        val imageCard =
//        Glide.with(requireContext())
//            .load(url)
//            .into(imageCard)

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}