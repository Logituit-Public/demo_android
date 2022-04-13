package com.mediacorp.moviedb.data.api

import com.google.gson.GsonBuilder
import com.mediacorp.moviedb.utils.AppUtilsConstant.API_KEY
import com.mediacorp.moviedb.utils.AppUtilsConstant.API_KEY_REQUEST_PARAM
import com.mediacorp.moviedb.utils.AppUtilsConstant.API_SESSION_TIMEOUT
import com.mediacorp.moviedb.utils.AppUtilsConstant.API_URL
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * ApiCallFactory responsible to create an instance of retrofit client
 * */
object ApiCallFactory {
    private fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(API_SESSION_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(API_SESSION_TIMEOUT, TimeUnit.SECONDS)

    private fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    private fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create(
        GsonBuilder().registerTypeAdapter(
            Date::class.java,
            DateDeserializer()
        ).create()
    )

    fun provideApi(
    ): ApiService {
        val client = provideOkHttpClientBuilder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter(API_KEY_REQUEST_PARAM, API_KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()
        return provideRetrofitBuilder().client(client)
            .baseUrl(API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(provideConverterFactory())
            .build()
            .create(ApiService::class.java)
    }

    private val nullOnEmptyConverterFactory = object : Converter.Factory() {
        fun converterFactory() = this
        override fun responseBodyConverter(
            type: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ) =
            object : Converter<ResponseBody, Any?> {
                val nextResponseBodyConverter =
                    retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)

                override fun convert(value: ResponseBody) =
                    if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
            }
    }
}
