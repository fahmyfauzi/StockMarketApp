package com.plcoding.stockmarketapp.domain.repository

import com.plcoding.stockmarketapp.domain.model.CompanyListing
import com.plcoding.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Antarmuka [StockRepository] menyediakan metode untuk mendapatkan daftar perusahaan.
 */
interface StockRepository {

    /**
     * Fungsi [getCompanyListings] digunakan untuk mendapatkan daftar perusahaan.
     *
     * @param fetchFromRemote Menentukan apakah data harus diambil dari sumber eksternal atau tidak.
     * @param query Kata kunci pencarian yang dapat digunakan untuk menyaring hasil (opsional).
     * @return Aliran ([Flow]) yang menghasilkan objek [Resource] yang berisi daftar perusahaan.
     */
    suspend fun getCompanyListings(
        fetchFromRemote:Boolean,
        query:String
    ): Flow<Resource<List<CompanyListing>>>
}