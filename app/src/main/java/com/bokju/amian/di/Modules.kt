package com.bokju.amian.di

import com.bokju.amian.placesearch.data.DefaultPlaceSearchRepository
import com.bokju.amian.placesearch.data.KtorRemotePlaceDataSource
import com.bokju.amian.placesearch.data.RemotePlaceDataSource
import com.bokju.amian.placesearch.domain.PlaceSearchRepository
import com.bokju.amian.placesearch.presentation.PlaceSearchViewModel
import com.bokju.amian.utils.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    single<HttpClientEngine> { OkHttp.create() }
    single { HttpClientFactory.create(get()) }

    singleOf(::KtorRemotePlaceDataSource).bind<RemotePlaceDataSource>()
    singleOf(::DefaultPlaceSearchRepository).bind<PlaceSearchRepository>()

    viewModelOf(::PlaceSearchViewModel)
}