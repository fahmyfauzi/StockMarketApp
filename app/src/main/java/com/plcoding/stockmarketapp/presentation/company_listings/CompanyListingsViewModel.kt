package com.plcoding.stockmarketapp.presentation.company_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.stockmarketapp.domain.repository.StockRepository
import com.plcoding.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject



/**
 * Kelas [CompanyListingsViewModel] adalah ViewModel yang bertanggung jawab
 * untuk menyediakan data perusahaan dan mengelola perubahan status dalam UI
 * untuk tampilan daftar perusahaan.
 *
 * @property repository Instance dari [StockRepository] untuk mengambil data perusahaan.
 */
@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {
    // Mutable state untuk menyimpan status dan data perusahaan yang dapat diubah.
    var state by mutableStateOf(CompanyListingsState())

    init {
        getCompanyListings()
    }

    // Job untuk melacak pekerjaan pencarian yang sedang berlangsung.
    private var searchJob:Job? =null

    /**
     * Fungsi [onEvent] untuk menanggapi perubahan event yang terjadi dalam tampilan daftar perusahaan.
     *
     * @param event Event yang terjadi pada tampilan daftar perusahaan.
     */
    fun onEvent(event:CompanyListingsEvent){
        when(event){
            is CompanyListingsEvent.Refresh ->{
                // Meminta ulang data perusahaan dari sumber eksternal.
                getCompanyListings(fetchFromRemote = true)
            }
            is CompanyListingsEvent.OnSearchQueryChange ->{
                // Memperbarui query pencarian dan memulai pencarian setelah jeda 500 ms.
                state = state.copy(searchOnQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyListings()
                }
            }
        }
    }

    /**
     * Fungsi [getCompanyListings] untuk mendapatkan daftar perusahaan berdasarkan query pencarian.
     *
     * @param query Kata kunci pencarian.
     * @param fetchFromRemote Menentukan apakah data harus diambil dari sumber eksternal atau tidak.
     */
    fun getCompanyListings(
        query:String=  state.searchOnQuery.lowercase(),
        fetchFromRemote:Boolean = false
    ){
        viewModelScope.launch {
            // Mengambil data perusahaan melalui repository.
            repository.getCompanyListings(fetchFromRemote,query)
                .collect{result->
                    when(result){
                        is Resource.Success ->{
                            // Jika data berhasil diambil, memperbarui data perusahaan dalam state.
                            result.data?.let {listing->
                                state = state.copy(
                                    companies = listing
                                )
                            }
                        }
                        is Resource.Error -> Unit // // Menangani kesalahan jika terjadi.
                        is Resource.Loading -> {
                            // Memperbarui status loading dalam state.
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }

    }
}