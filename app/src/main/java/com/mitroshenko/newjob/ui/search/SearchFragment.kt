package com.mitroshenko.newjob.ui.search

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.mitroshenko.newjob.adapter.RecommendationAdapter
import com.mitroshenko.newjob.databinding.FragmentSearchBinding
import com.mitroshenko.newjob.retrofit.SF_Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : Fragment() {

    private val adapter = RecommendationAdapter()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

        binding.rcRecommendations.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rcRecommendations.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val sf_Api = retrofit.create(SF_Api::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val list = sf_Api.getAllRecommendation()
            runOnUiThread {
                binding.apply {
                    adapter.submitList(list.offers)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



//    private fun init(){
//        binding.apply {
//            val rc_recom: RecyclerView = binding.rcRecommendations
//            rcRecommendations.adapter = adapter
//            rc_recom.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
//            val imageIdList = listOf(
//                R.drawable.near_vacancies,
//                R.drawable.level_up_resume,
//                R.drawable.ic_search)
//            var index = 0
//            btnAdd.setOnClickListener {
//                if (index>3) index = 0
//                val recom = Recommendations(imageIdList[index], "Recom $index")
//                adapter.addRecommendations(recom)
//                index++
//            }
//        }
//    }
}