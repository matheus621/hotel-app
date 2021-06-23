package com.example.hoteisapp.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.fragment.app.ListFragment
import com.example.hoteisapp.database.SQLiteRepository
import com.example.hoteisapp.model.Hotel
import com.example.hoteisapp.presenter.HotelListPresenter
import com.example.hoteisapp.repository.HotelRepository
import com.example.hoteisapp.repository.MemoryRepository
import com.example.hoteisapp.view.HotelListView
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HotelListFragment: ListFragment(), HotelListView {
    private val presenter: HotelListPresenter by inject { parametersOf(this) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.searchHotels("")
    }

    override fun showHotels(hotels: List<Hotel>) {
        val adapter = com.example.hoteisapp.Adapter(requireContext(),hotels)
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