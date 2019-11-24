package com.franck.cedric.musicapp.domain.io.deezer

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val deezerRxService = Retrofit.Builder()
    .baseUrl(DeezerApi.BASE_PATH)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()
    .create(DeezerApi::class.java)
