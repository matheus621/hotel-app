package com.example.hoteisapp.repository

import com.example.hoteisapp.model.Hotel
import javax.security.auth.callback.Callback

interface HotelRepository {
    fun save(hotel: Hotel)
    fun remove(vararg hotels: Hotel)
    fun hotelById(id: Long, callback: (Hotel?) -> Unit)
    fun search(term: String, callback: (List<Hotel>) -> Unit)

}