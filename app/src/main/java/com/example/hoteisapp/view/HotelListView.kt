package com.example.hoteisapp.view

import com.example.hoteisapp.model.Hotel

interface HotelListView {
    fun showHotels(hotels: List<Hotel>)
    fun showHotelDetails(hotel: Hotel)
}