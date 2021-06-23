package com.example.hoteisapp.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
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
        if (activity is OnHotelClickListener){
            val listener = activity as OnHotelClickListener
            listener.onHotelClick(hotel)
        }
    }

    override fun showDeleteMode() {
        TODO("Not yet implemented")
    }

    override fun hideDeleteMode() {
        TODO("Not yet implemented")
    }

    override fun showSelectedHotels(hotels: List<Hotel>) {
        TODO("Not yet implemented")
    }

    override fun updateSelectionCountText(count: Int) {
        TODO("Not yet implemented")
    }

    override fun showMessageHotelsDeleted(count: Int) {
        TODO("Not yet implemented")
    }


    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val hotel = l?.getItemAtPosition(position) as Hotel
        presenter.showHotelDetails(hotel)
    }

    interface OnHotelClickListener{
        fun onHotelClick(hotel: Hotel)
    }

    fun search(text: String){
        presenter.searchHotels(text)
    }

    fun clearSearch(){
        presenter.searchHotels("")
    }

}