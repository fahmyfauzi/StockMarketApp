package com.plcoding.stockmarketapp.domain.model

//Domain merujuk pada bagian dari aplikasi yang berisi logika bisnis inti dan aturan yang mencerminkan pemahaman mendalam tentang masalah yang sedang diatasi oleh aplikasi tersebut.

/**
* Data class [CompanyListing] merupakan representasi model domain untuk informasi perusahaan.
*
* @property name Nama perusahaan.
* @property symbol Simbol saham perusahaan.
* @property exchange Bursa saham tempat saham perusahaan terdaftar.
*/
data class CompanyListing(
    val name:String,
    val symbol:String,
    val exchange:String,
)
