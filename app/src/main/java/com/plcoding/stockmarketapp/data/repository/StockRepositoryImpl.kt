package com.plcoding.stockmarketapp.data.repository

import com.plcoding.stockmarketapp.data.csv.CSVParser
import com.plcoding.stockmarketapp.data.local.StockDatabase
import com.plcoding.stockmarketapp.data.mapper.toCompanyListing
import com.plcoding.stockmarketapp.data.mapper.toCompanyListingEntity
import com.plcoding.stockmarketapp.data.remote.StockApi
import com.plcoding.stockmarketapp.domain.model.CompanyListing
import com.plcoding.stockmarketapp.domain.repository.StockRepository
import com.plcoding.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Kelas [StockRepositoryImpl] adalah implementasi dari [StockRepository] yang mengelola
 * pengambilan data dari sumber eksternal (API) atau penyimpanan lokal (basis data).
 *
 * @property api Instance dari [StockApi] untuk melakukan panggilan ke API.
 * @property db Instance dari [StockDatabase] untuk mengakses basis data lokal.
 */
@Singleton
class StockRepositoryImpl  @Inject constructor(
    private val api:StockApi,
    private val db:StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>
) : StockRepository{

    private val dao = db.dao


    /**
     * Fungsi [getCompanyListings] mengambil daftar perusahaan dari sumber eksternal (API) atau basis data lokal.
     *
     * @param fetchFromRemote Menentukan apakah data harus diambil dari sumber eksternal atau tidak.
     * @param query Kata kunci pencarian yang dapat digunakan untuk menyaring hasil (opsional).
     * @property companyListingParser Instance dari [CSVParser] untuk parsing data CSV menjadi objek [CompanyListing].
     * @return Aliran ([Flow]) yang menghasilkan objek [Resource] yang berisi daftar perusahaan.
     */
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))

            // Ambil daftar perusahaan dari basis data lokal
            val localListings  = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            // Cek apakah basis data lokal kosong dan query kosong
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            // Jika tidak perlu mengambil dari sumber eksternal, emit hasil dari basis data lokal
            if(shouldJustLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListing = try {
                val response = api.getListings()
                companyListingParser.parse(response.byteStream())

            }catch (e: IOException){
                // Tangani kesalahan IO (misalnya, ketika tidak ada koneksi internet)
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null

            }catch (e: HttpException){
                // Tangani kesalahan HTTP (misalnya, respons gagal dari server)
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            // Jika data dari sumber eksternal berhasil diambil, simpan ke basis data lokal.
            remoteListing?.let {listings->
                // Update basis data lokal dengan data yang baru diambil dari sumber eksternal
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                // Emit hasil akhir yang terdiri dari data dari basis data lokal setelah update
                emit(Resource.Success(
                    data  = dao
                        .searchCompanyListing("")
                        .map{it.toCompanyListing()}
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}