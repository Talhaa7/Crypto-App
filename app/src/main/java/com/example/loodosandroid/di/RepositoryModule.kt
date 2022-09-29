package com.example.loodosandroid.di

import com.example.loodosandroid.data.CryptoRepositoryImp
import com.example.loodosandroid.domain.base.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCryptoRepository(cryptoRepositoryImp: CryptoRepositoryImp) : CryptoRepository
}