package com.dicoding.naufal.footballteamsample.data

import com.dicoding.naufal.footballteamsample.data.remote.FootballTeamServices
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory{
    const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"

    fun footballServices() : FootballTeamServices {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(FootballTeamServices::class.java)
    }

    //HttpLoggingInterceptor untuk log dari proses HTTP (Cek Logcat, Verbose)
    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    //Untuk yang ngatur HTTP nya kita pake OkHttp (penjelasan baca ini : https://dennytobing.wordpress.com/2016/12/19/retrofit-vs-okhttp/)
    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val b = OkHttpClient.Builder()
        b.addInterceptor(interceptor)
        return b.build()
    }

}