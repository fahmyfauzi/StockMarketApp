package com.plcoding.stockmarketapp.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface StockApi digunakan untuk mendefinisikan endpoint-endpoint API yang terkait dengan informasi saham.
 */

interface StockApi {

    /**
     * Fungsi [getListings] digunakan untuk mengambil daftar status pencatatan saham dari API.
     *
     * @param apiKey Kunci API yang diperlukan untuk mengakses layanan.
     * @return Respon dari API dalam bentuk [ResponseBody].
     */
    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey:String
    ):ResponseBody

    companion object{
        // Kunci API yang digunakan untuk mengakses layanan.
        const val API_KEY ="Q63Y9NX3TUF587NF"

        // URL dasar dari layanan API Stock.
        const val BASE_URL = "https://alphavantage.co"
    }
}