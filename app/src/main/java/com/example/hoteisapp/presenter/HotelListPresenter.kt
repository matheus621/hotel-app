package com.example.hoteisapp.presenter

import com.example.hoteisapp.model.Hotel
import com.example.hoteisapp.repository.HotelRepository
import com.example.hoteisapp.view.HotelListView

class HotelListPresenter(private val view: HotelListView, private val repository: HotelRepository) {

    fun searchHotels(term: String) {
        repository.search(term) { hotels ->
            view.showHotels(hotels)
        }
    }

    fun showHotelDetails(hotel: Hotel) {
        view.showHotelDetails(hotel)
    }

}