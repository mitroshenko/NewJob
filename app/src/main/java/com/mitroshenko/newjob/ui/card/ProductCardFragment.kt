package com.mitroshenko.newjob.ui.card

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.adapter.ReviewsAdapter
import com.mitroshenko.newjob.databinding.ActivityCodeBinding.bind
import com.mitroshenko.newjob.databinding.FragmentProductCardBinding
import com.mitroshenko.newjob.presentation.MainActivity
import com.mitroshenko.newjob.retrofit.product.Product
import com.mitroshenko.newjob.retrofit.product.ProductApi
import com.mitroshenko.newjob.retrofit.product.Review
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductCardFragment : Fragment() {
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
            val hello = arguments?.getString("key")
            binding.tvPrice1.text = hello



            val interceptor2 = HttpLoggingInterceptor()
            interceptor2.level = HttpLoggingInterceptor.Level.BODY

            val client2 = OkHttpClient.Builder()
                .addInterceptor(interceptor2)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com")
                .client(client2)
                .addConverterFactory(GsonConverterFactory.create()).build()
            val sf_Api = retrofit.create(ProductApi::class.java)

//            CoroutineScope(Dispatchers.IO).launch {
//                val list = sf_Api.getCardById(1)
//                activity?.runOnUiThread {
//                    binding.apply {
//                        revadapter.submitList(list.reviews)
//                    }
//                }
//            }
//            CoroutineScope(Dispatchers.IO).launch {
//                val model = sf_Api.getCardById(1)
//                activity?.runOnUiThread {
//                    binding.apply {
//                        tvPrice2.text = model.price.toString()
//                        tvTitle.text = model.title
//                        tvDescription.text = model.description
//                        tvRaiting.text = model.rating.toString()
//                        tvBrand.text = model.brand
//                        tvPrice1.text = "Price"
//                        tvDescriptionTitle.text = "Description"
//                        tvReviews.text = "Reviews"
//                    }
//                }
//            }
            return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}