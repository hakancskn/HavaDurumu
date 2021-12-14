package com.hakancskn.havadurumu.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hakancskn.havadurumu.R
import com.hakancskn.havadurumu.adapter.SearchAdapter
import com.hakancskn.havadurumu.viewmodel.LocationViewModel
import com.hakancskn.havadurumu.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_location.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {


    private lateinit var viewModel: SearchViewModel
    private val searchAdapter = SearchAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        viewModel.getSharedPreferencesSearchData(requireContext())

        search_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 == null || p0.isEmpty()) {
                    viewModel.getSharedPreferencesSearchData(requireContext())
                } else {
                    viewModel.getAutoComplete(p0.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        val layoutManager = LinearLayoutManager(context)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        search_recyclerView.layoutManager = layoutManager

        search_recyclerView.adapter = searchAdapter

    }

    private fun observeLiveData() {
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        viewModel.autoCompleteListLoading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading) {
                searchLoading.visibility = View.VISIBLE
                searchError.visibility = View.GONE
            } else {
                searchLoading.visibility = View.GONE
            }
        })

        viewModel.autoCompleteListError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    searchError.visibility = View.VISIBLE
                } else {
                    searchError.visibility = View.GONE
                }
            }
        })
        viewModel.autoCompleteList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {

                search_recyclerView.visibility = View.VISIBLE
                searchAdapter.updateSearchList(list)
            }

        })
    }

}