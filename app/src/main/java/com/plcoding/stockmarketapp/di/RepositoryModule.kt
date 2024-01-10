package com.plcoding.stockmarketapp.di

import com.plcoding.stockmarketapp.data.csv.CSVParser
import com.plcoding.stockmarketapp.data.csv.CompanyListingsParser
import com.plcoding.stockmarketapp.data.repository.StockRepositoryImpl
import com.plcoding.stockmarketapp.domain.model.CompanyListing
import com.plcoding.stockmarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module [RepositoryModule] yang menyediakan dependensi terkait repository,
 * seperti [CompanyListingsParser] dan [StockRepositoryImpl].
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Fungsi [bindCompanyListingsParser] mengikat implementasi [CompanyListingsParser]
     * ke antarmuka [CSVParser<CompanyListing>].
     *
     * @param companyListingsParser Instance dari [CompanyListingsParser].
     * @return Instance dari [CSVParser<CompanyListing>].
     */
    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser:CompanyListingsParser
    ) : CSVParser<CompanyListing>

    /**
     * Fungsi [bindStockRepository] mengikat implementasi [StockRepositoryImpl]
     * ke antarmuka [StockRepository].
     *
     * @param stockRepositoryImpl Instance dari [StockRepositoryImpl].
     * @return Instance dari [StockRepository].
     */
    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl : StockRepositoryImpl
    ): StockRepository
}