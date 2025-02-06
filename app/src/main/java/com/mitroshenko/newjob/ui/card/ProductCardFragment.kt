package com.mitroshenko.newjob.ui.card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.adapter.ReviewsAdapter
import com.mitroshenko.newjob.databinding.FragmentBasketBinding
import com.mitroshenko.newjob.databinding.FragmentProductCardBinding
import com.mitroshenko.newjob.retrofit.product.SF_Api
import com.mitroshenko.newjob.retrofit.reviews.CF_Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductCardFragment : Fragment() {

    private var revAdapter = ReviewsAdapter()
    private var _binding: FragmentProductCardBinding? = null
    private val binding get() = _binding!!
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            _binding = FragmentProductCardBinding.inflate(inflater, container, false)
            val root: View = binding.root
            binding.rcReviews.layoutManager = LinearLayoutManager(context)
            binding.rcReviews.adapter = revAdapter
            binding.ibBack.setOnClickListener {
                view?.findNavController()!!
                    .navigate(R.id.action_productCardFragment_to_navigation_search)
            }

            val interceptor2 = HttpLoggingInterceptor()
            interceptor2.level = HttpLoggingInterceptor.Level.BODY

            val client2 = OkHttpClient.Builder()
                .addInterceptor(interceptor2)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com")
                .client(client2)
                .addConverterFactory(GsonConverterFactory.create()).build()
            val cf_Api = retrofit.create(CF_Api::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                val list = cf_Api.getCardById()
                activity?.runOnUiThread {
                    binding.apply {
                        revAdapter.submitList(list.reviews)
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