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
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.adapter.ReviewsAdapter
import com.mitroshenko.newjob.databinding.FragmentProductCardBinding
import com.mitroshenko.newjob.data.api.ProductApi
import com.mitroshenko.newjob.domain.usecases.LoadProductCard
import com.mitroshenko.newjob.presentation.ui.IdCardModel
import com.mitroshenko.newjob.presentation.ui.search.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        }

//        val client = OkHttpClient.Builder()
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://dummyjson.com")
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create()).build()
//        val sf_Api = retrofit.create(ProductApi::class.java)

//        CoroutineScope(Dispatchers.IO).launch {
//            val list = sf_Api.getCardById(1)
//            activity?.runOnUiThread {
//                binding.apply {
//                    revadapter.submitList(list.reviews)
//                }
//            }
//        }
//        CoroutineScope(Dispatchers.IO).launch {
//            val model = sf_Api.getCardById(1)
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
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}