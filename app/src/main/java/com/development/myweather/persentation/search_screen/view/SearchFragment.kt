package com.development.myweather.persentation.search_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.development.myweather.R
import com.development.myweather.databinding.FragmentSearchBinding
import com.development.myweather.persentation.detail_screen.view.DetailFragmentSearch
import com.development.myweather.persentation.search_screen.view_model.SearchViewModel
import com.development.myweather.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        binding.btnSearch.setOnClickListener {
            val inputCity = binding.inputCity.text.toString()
            viewModel.getSearchWeater(inputCity)
            viewModel.serachWeather.observe(viewLifecycleOwner){it ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, DetailFragmentSearch(it))
                    //.addToBackStack(null) // tambahkan backstage agar bisa kembali ke fragment sebelumnya
                    .commit()
            }
        }
    }

}