package com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.rest

import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.tools.Constantes
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestServicio {
    private var oRetrofit: Retrofit? = null

    public fun getServicio(): Retrofit?
    {
        if(oRetrofit==null)
        {
            val gson = GsonBuilder().setLenient().create()
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            oRetrofit = Retrofit.Builder()
                .baseUrl(Constantes.ENDPOINT)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return oRetrofit;
    }
}