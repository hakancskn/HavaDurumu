package com.hakancskn.havadurumu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hakancskn.havadurumu.R
import com.hakancskn.havadurumu.databinding.ItemSearchBinding
import com.hakancskn.havadurumu.model.AutoComplete
import com.hakancskn.havadurumu.util.addSearchData
import com.hakancskn.havadurumu.view.SearchFragmentDirections

class SearchAdapter(private val searchList: ArrayList<AutoComplete>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(var view: ItemSearchBinding) : RecyclerView.ViewHolder(view.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemSearchBinding>(
            inflater,
            R.layout.item_search,
            parent,
            false
        )
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        holder.view.autocomplete = searchList[position]
        holder.view.searchItemLayout.setOnClickListener(View.OnClickListener {
            addSearchData(it.context, searchList[position])
            val action =
                SearchFragmentDirections.actionSearchFragmentToLocationFragment(searchList[position].cityKey.toString())
            Navigation.findNavController(it).navigate(action)
        })

    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    fun updateSearchList(updateSearchList: List<AutoComplete>) {
        searchList.clear()
        searchList.addAll(updateSearchList)
        notifyDataSetChanged()
    }


}
