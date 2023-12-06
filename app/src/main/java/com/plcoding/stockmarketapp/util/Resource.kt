package com.plcoding.stockmarketapp.util

/**
 * Kelas segel [Resource] digunakan untuk menyatakan status hasil operasi yang berisi data dan pesan.
 *
 * @param T Jenis data yang disertakan dalam [Resource].
 * @property data Data yang dihasilkan dari operasi.
 * @property message Pesan yang menjelaskan status atau kesalahan operasi.
 */

sealed class Resource<T>(val data:T?=null, val message:String?=null){

    /**
     * Kelas [Success] adalah subkelas [Resource] yang menunjukkan operasi berhasil.
     *
     * @param data Data yang dihasilkan dari operasi.
     */
    class Success<T>(data: T?):Resource<T>(data)


    /**
     * Kelas [Error] adalah subkelas [Resource] yang menunjukkan terjadinya kesalahan selama operasi.
     *
     * @param message Pesan yang menjelaskan kesalahan operasi.
     * @param data Data yang dihasilkan dari operasi (opsional).
     */
    class Error<T>(message: String,data: T? = null): Resource<T>(data,message)

    /**
     * Kelas [Loading] adalah subkelas [Resource] yang menunjukkan bahwa operasi sedang berlangsung.
     *
     * @param isLoading Status loading, true jika sedang loading, false jika tidak.
     */
    class Loading<T>(val isLoading:Boolean= true):Resource<T>(null)

}