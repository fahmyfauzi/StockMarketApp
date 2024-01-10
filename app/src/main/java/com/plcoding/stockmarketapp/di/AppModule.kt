package com.plcoding.stockmarketapp.di

import android.app.Application
import androidx.room.Room
import com.plcoding.stockmarketapp.data.local.StockDatabase
import com.plcoding.stockmarketapp.data.remote.StockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


/**
 * Dagger Hilt module [AppModule] berisi definisi-providing dependencies (ketergantungan)
 * yang digunakan dalam aplikasi, seperti [StockApi] dan [StockDatabase].
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Fungsi [provideStockApi] memberikan instance [StockApi] yang digunakan untuk berkomunikasi dengan API.
     *
     * @return Instance dari [StockApi] yang sudah di-configurasi.
     */
    @Provides
    @Singleton
    fun provideStockApi(): StockApi{
        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    /**
     * Fungsi [provideStockDatabase] memberikan instance [StockDatabase] yang digunakan untuk akses lokal ke basis data.
     *
     * @param app Instance dari [Application] untuk mengakses konteks aplikasi.
     * @return Instance dari [StockDatabase] yang sudah di-configurasi.
     */
    @Provides
    @Singleton
   fun provideStockDatabase(app: Application):StockDatabase{
       return Room.databaseBuilder(
           app,
           StockDatabase::class.java,
           "stockdb"
       ).build()
   }
}