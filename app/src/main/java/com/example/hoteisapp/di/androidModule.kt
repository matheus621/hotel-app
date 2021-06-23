package com.example.hoteisapp.di

import com.example.hoteisapp.database.SQLiteRepository
import com.example.hoteisapp.presenter.HotelDetailsPresenter
import com.example.hoteisapp.presenter.HotelFormPresenter
import com.example.hoteisapp.presenter.HotelListPresenter
import com.example.hoteisapp.repository.HotelRepository
import com.example.hoteisapp.view.HotelDetailsView
import com.example.hoteisapp.view.HotelFormView
import com.example.hoteisapp.view.HotelListView
import org.koin.dsl.module.module

val androidModule = module {
    single { this }
    single {
        SQLiteRepository(ctx = get()) as HotelRepository
    }
    factory { (view: HotelListView) ->
        HotelListPresenter(
            view,
            repository = get()
        )
    }
    factory { (view: HotelDetailsView) ->
        HotelDetailsPresenter(
            view,
            repository = get()
        )
    }
    factory { (view: HotelFormView) ->
        HotelFormPresenter(
            view,
            repository = get()
        )
    }
}