package com.plcoding.stockmarketapp.presentation.company_listings

import com.plcoding.stockmarketapp.domain.model.CompanyListing

/**
 * Data class [CompanyListingsState] merepresentasikan status dan data perusahaan
 * yang digunakan dalam tampilan daftar perusahaan.
 *
 * @property companies Daftar perusahaan yang akan ditampilkan.
 * @property isLoading Menentukan apakah sedang dalam proses pengambilan data.
 * @property isRefreshing Menentukan apakah tampilan sedang dalam proses penyegaran data.
 * @property searchOnQuery Kata kunci pencarian yang digunakan untuk menyaring data perusahaan.
 */
data class CompanyListingsState(
    val companies : List<CompanyListing> = emptyList(),
    val isLoading:Boolean = false,
    val isRefreshing:Boolean = false,
    val searchOnQuery:String = ""
)
