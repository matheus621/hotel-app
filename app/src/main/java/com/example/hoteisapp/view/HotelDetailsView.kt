package com.example.hoteisapp.view

import com.example.hoteisapp.model.Hotel

interface HotelDetailsView {
    fun showHotelDetails(hotel: Hotel)
    fun errorHotelNotFound()
}