package com.plcoding.stockmarketapp.data.csv

import com.plcoding.stockmarketapp.domain.model.CompanyListing
import java.io.InputStream

// Interface CSVParser bertujuan untuk memetakan kontrak pemrosesan data CSV.
interface CSVParser<T> {
    // Fungsi suspend parse akan diimplementasikan untuk melakukan parsing dari InputStream menjadi List<T>.
    // Fungsi ini bersifat suspending, artinya dapat berjalan secara asynchronous tanpa menghentikan eksekusi program.
    suspend fun parse(stream: InputStream):List<T>
}