package com.plcoding.stockmarketapp.data.mapper

import com.plcoding.stockmarketapp.data.local.CompanyListingEntity
import com.plcoding.stockmarketapp.domain.model.CompanyListing


//MAPPER komponen yang bertanggung jawab untuk mengonversi data dari satu bentuk atau representasi ke bentuk atau representasi lainnya. contohnya disini mengonversi entitas ke domain atau sebaliknya

/**
 * Fungsi ekstensi [toCompanyListing] pada [CompanyListingEntity] digunakan untuk mengonversi entitas
 * [CompanyListingEntity] menjadi model domain [CompanyListing].
 *
 * @return Objek [CompanyListing] yang telah diisi dengan data dari [CompanyListingEntity].
 */
fun CompanyListingEntity.toCompanyListing(): CompanyListing{
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

/**
 * Fungsi ekstensi [toCompanyListingEntity] pada [CompanyListing] digunakan untuk mengonversi model domain
 * [CompanyListing] menjadi entitas [CompanyListingEntity].
 *
 * @return Objek [CompanyListingEntity] yang telah diisi dengan data dari [CompanyListing].
 */
fun CompanyListing.toCompanyListingEntity():CompanyListingEntity{
    return CompanyListingEntity(
        name= name,
        symbol=symbol,
        exchange=exchange
    )
}