package com.plcoding.stockmarketapp.data.csv

import com.opencsv.CSVReader
import com.plcoding.stockmarketapp.domain.model.CompanyListing
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

// Anotasi @Singleton menandakan bahwa hanya ada satu instance dari kelas ini yang akan diinject.

@Singleton
class CompanyListingsParser @Inject constructor() : CSVParser<CompanyListing> {
    /**
     * Fungsi suspend [parse] mengonversi input stream dari data CSV menjadi daftar objek [CompanyListing].
     *
     * @param stream InputStream yang berisi data CSV.
     * @return List<CompanyListing> yang berisi daftar perusahaan yang telah di-parse dari data CSV.
     */
    override suspend fun parse(stream: InputStream): List<CompanyListing> {
        // Membuat instance CSVReader untuk membaca data CSV dari InputStream.
        val csvReader = CSVReader(InputStreamReader(stream))

        // Membaca semua baris dalam file CSV, mengabaikan baris pertama (header).
        return csvReader
            .readAll()
            .drop(1)

            // Mapped dan di-filter untuk menghasilkan daftar objek CompanyListing.
            .mapNotNull { line->
                val symbol = line.getOrNull(0)
                val name = line.getOrNull(1)
                val exchange = line.getOrNull(2)

                // Membuat objek CompanyListing dan melakukan null-check pada atribut penting.
                CompanyListing(
                    name= name ?: return@mapNotNull null,
                    symbol= symbol ?: return@mapNotNull null,
                    exchange = exchange ?: return@mapNotNull null
                )
            }.also {
                // Menutup CSVReader setelah proses selesai.
                csvReader.close()
            }
    }
}