package com.example.hoteisapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import com.example.hoteisapp.R
import com.example.hoteisapp.model.Hotel
import com.example.hoteisapp.presenter.HotelDetailsPresenter
import com.example.hoteisapp.view.HotelDetailsView
import kotlinx.android.synthetic.main.fragment_details_hotel.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HotelDetailsFragment : Fragment(), HotelDetailsView {

    private val presenter: HotelDetailsPresenter by inject { parametersOf(this) }
    private var hotel: Hotel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//    }

    companion object {
        const val TAG_DETAILS = "tagDetalhe"
        const val EXTRA_HOTEL_ID = "hotelId"
        fun newInstance(id: Long) = HotelDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(
                    EXTRA_HOTEL_ID, id
                )
            }
        }
    }
}