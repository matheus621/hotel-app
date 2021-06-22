package com.example.hoteisapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hoteisapp.R
import com.example.hoteisapp.model.Hotel
import com.example.hoteisapp.presenter.HotelDetailsPresenter
import com.example.hoteisapp.repository.MemoryRepository
import com.example.hoteisapp.view.HotelDetailsView
import kotlinx.android.synthetic.main.fragment_details_hotel.*

class HotelDetailsFragment : Fragment(), HotelDetailsView {

    private val presenter = HotelDetailsPresenter(this, MemoryRepository)
    private var hotel: Hotel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details_hotel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadHotelDetails(arguments?.getLong(EXTRA_HOTEL_ID, -1) ?: -1)
    }

    override fun showHotelDetails(hotel: Hotel) {
        this.hotel = hotel
        txtName.text = hotel.name
        txtAddress.text = hotel.address
        rtbRating.rating = hotel.rating
    }

    override fun errorHotelNotFound() {
        txtName.text = getString(R.string.error_hotel_not_found)
        txtAddress.visibility = View.GONE
        rtbRating.visibility = View.GONE
    }

    companion object {
        const val TAG_DETAILS = "tagDetalhe"
        private const val EXTRA_HOTEL_ID = "hotelId"
        fun newInstance(id: Long) = HotelDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(
                    EXTRA_HOTEL_ID, id
                )
            }
        }
    }
}