package com.mitroshenko.newjob.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.adapter.RecommendationAdapter
import com.mitroshenko.newjob.adapter.Recommendations
import com.mitroshenko.newjob.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val adapter = RecommendationAdapter()


    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun init(){
        binding.apply {
            val rc_recom: RecyclerView = binding.rcRecommendations
            rcRecommendations.adapter = adapter
            rc_recom.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
            val imageIdList = listOf(
                R.drawable.near_vacancies,
                R.drawable.level_up_resume,
                R.drawable.ic_search)
            var index = 0
            btnAdd.setOnClickListener {
                if (index>3) index = 0
                val recom = Recommendations(imageIdList[index], "Recom $index")
                adapter.addRecommendations(recom)
                index++
            }
        }
    }

}