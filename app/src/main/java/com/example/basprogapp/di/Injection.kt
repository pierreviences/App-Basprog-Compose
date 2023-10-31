package com.example.basprogapp.di

import com.example.basprogapp.data.BasprogRepository

object Injection {
    fun provideRepository(): BasprogRepository {
        return BasprogRepository.getInstance()
    }
}