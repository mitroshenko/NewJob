package com.mitroshenko.newjob.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.adapter.ProductAdapter
import com.mitroshenko.newjob.databinding.FragmentSearchBinding
import com.mitroshenko.newjob.presentation.MainActivity
import com.mitroshenko.newjob.retrofit.product.Product
import com.mitroshenko.newjob.retrofit.product.ProductApi
import com.mitroshenko.newjob.retrofit.product.Products
import com.mitroshenko.newjob.ui.card.ProductCardFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : Fragment() {

    private val adapter: ProductAdapter by lazy {
        ProductAdapter { product ->
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
//        val searchViewModel =
//            ViewModelProvider(this).get(SearchViewModel::class.java)


        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rcRecommendations.layoutManager = GridLayoutManager(context, 2)
        binding.rcRecommendations.adapter = adapter
        binding.rcRecommendations.setOnClickListener {
            val fragment = SearchFragment()
            val bundle = Bundle()
            bundle.putInt("key", 1)
            fragment.arguments = bundle
            view?.findNavController()!!
            .navigate(R.id.action_navigation_search_to_productCardFragment)

    }
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val sf_Api = retrofit.create(ProductApi::class.java)

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
//                CoroutineScope(Dispatchers.IO).launch {
//                    val list = text?.let { sf_Api.getProductsByName(it) }
//                    activity?.runOnUiThread {
//                        binding.apply {
//                            adapter.submitList(list?.products)
//                        }
//                    }
//                }
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                CoroutineScope(Dispatchers.IO).launch {
                    val list = text?.let { sf_Api.getProductsByName(it) }
                    activity?.runOnUiThread {
                        binding.apply {
                            adapter.submitList(list?.products)
                        }
                    }
                }
                return true
            }
        })
        CoroutineScope(Dispatchers.IO).launch {
            val list = sf_Api.getAllProducts()
            activity?.runOnUiThread {
                binding.apply {
                    adapter.submitList(list.products)
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