package com.mitroshenko.newjob.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.adapter.ProductAdapter
import com.mitroshenko.newjob.databinding.FragmentSearchBinding
import com.mitroshenko.newjob.data.api.ProductApi
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
        val searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rcRecommendations.layoutManager = GridLayoutManager(context, 2)
        binding.rcRecommendations.adapter = adapter
        binding.rcRecommendations.setOnClickListener {
            val fragment = SearchFragment()
            val bundle = Bundle()
            bundle.putString("key", "Hello")
            fragment.arguments = bundle
            view?.findNavController()!!
            .navigate(R.id.action_navigation_search_to_productCardFragment)
//            searchViewModel.productList.observe(viewLifecycleOwner, {
//                adapter.submitList(it)
//            })

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
            val url = "https://co14tula-r71.gosuslugi.ru/netcat_files/45/255/385289_maslenica_dlya_detey_6_7_let_27.jpg"
            val imageCard = binding.imageView4
            Glide.with(context)
                .load(url)
                .into(imageCard)
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