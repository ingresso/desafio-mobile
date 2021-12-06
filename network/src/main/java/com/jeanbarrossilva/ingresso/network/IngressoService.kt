package com.jeanbarrossilva.ingresso.network

import com.jeanbarrossilva.ingresso.network.dto.MovieDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.create
import retrofit2.http.GET

/** Service interface for executing HTTP requests to the given Ingresso API. **/
interface IngressoService {
    @GET("events/coming-soon/partnership/desafio")
    fun getMovies(): Observable<List<MovieDto>>

    companion object {
        /** Creates an instance of [IngressoService] through [Retrofit.Builder]. **/
        operator fun invoke(): IngressoService {
            return Retrofit.Builder()
                .baseUrl("https://api-content.ingresso.com/v0/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create()
        }
    }
}