package com.example.hoteisapp.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import com.example.hoteisapp.model.Hotel
import com.example.hoteisapp.presenter.HotelListPresenter
import com.example.hoteisapp.repository.MemoryRepository
import com.example.hoteisapp.view.HotelListView

class HotelListFragment: ListFragment(), HotelListView {
    private val presenter = HotelListPresenter(this, MemoryRepository)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.searchHotels("")
    }

    override fun showHotels(hotels: List<Hotel>) {
        val adapter = ArrayAdapter<Hotel>(requireContext(), android.R.layout.simple_list_item_1, hotels)
        listAdapter = adapter
    }

    override fun showHotelDetails(hotel: Hotel) {
        TODO("Not yet implemented")
    }

}