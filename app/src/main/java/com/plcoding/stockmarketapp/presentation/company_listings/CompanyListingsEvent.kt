package com.plcoding.stockmarketapp.presentation.company_listings

/**
 * Sealed class [CompanyListingsEvent] merepresentasikan event yang dapat terjadi
 * dalam tampilan daftar perusahaan.
 */
sealed class CompanyListingsEvent{
    // Object untuk merepresentasikan event penyegaran (refresh) data perusahaan.
    object Refresh:CompanyListingsEvent()

    // Data class untuk merepresentasikan perubahan pada query pencarian.
    data class OnSearchQueryChange(val query:String):CompanyListingsEvent()
}