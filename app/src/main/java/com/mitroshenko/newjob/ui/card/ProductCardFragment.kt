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
import com.mitroshenko.newjob.adapter.ProductAdapter
import com.mitroshenko.newjob.adapter.ReviewsAdapter
import com.mitroshenko.newjob.databinding.FragmentBasketBinding
import com.mitroshenko.newjob.databinding.FragmentProductCardBinding
import com.mitroshenko.newjob.retrofit.product.Product
import com.mitroshenko.newjob.retrofit.product.SF_Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductCardFragment : Fragment() {

//    private val adapter: ProductAdapter by lazy {
//        ProductAdapter { product ->
//            view?.findNavController()!!
//                .navigate(R.id.action_navigation_search_to_productCardFragment)
//        }
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

            val interceptor2 = HttpLoggingInterceptor()
            interceptor2.level = HttpLoggingInterceptor.Level.BODY

            val client2 = OkHttpClient.Builder()
                .addInterceptor(interceptor2)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com")
                .client(client2)
                .addConverterFactory(GsonConverterFactory.create()).build()
            val sf_Api = retrofit.create(SF_Api::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                val list = sf_Api.getCardById(1)
                activity?.runOnUiThread {
                    binding.apply {
                        revadapter.submitList(list.reviews1)
                    }
                }
            }
            return root
    }
//    fun add(products: Product) = with(binding) {
//        tvPrice2.text = products.price.toString()
//        tvRaiting.text = products.rating.toString()
//        tvBrand.text = products.brand
//        tvDescription.text = products.description
//        tvTitle.text = products.title
//    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}