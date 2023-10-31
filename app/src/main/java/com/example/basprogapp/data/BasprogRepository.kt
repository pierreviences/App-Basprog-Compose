package com.example.basprogapp.data

import com.example.basprogapp.model.Basprog
import com.example.basprogapp.model.BasprogData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class BasprogRepository {
    private val listBasprog = mutableListOf<Basprog>()

    init {
        if (listBasprog.isEmpty()) {
            BasprogData.basprogs.forEach {
                listBasprog.add(it)
            }
        }
    }

    fun getBasprog(): Flow<List<Basprog>> = flowOf(listBasprog)

    fun searchBasprog(query: String): Flow<List<Basprog>> =
        flowOf(listBasprog.filter { it.name.contains(query, ignoreCase = true) })

    fun getBasprogById(id: String): Basprog = listBasprog.first { it.id == id }

    fun getFavoriteBasprog(): Flow<List<Basprog>> = flowOf(listBasprog.filter { it.isFavorite })

    fun updateFavoriteBasprog(id: String, newValue: Boolean): Flow<Boolean> =
        flowOf(listBasprog.indexOfFirst { it.id == id }.takeIf { it >= 0 }?.run {
            listBasprog[this] = listBasprog[this].copy(isFavorite = newValue)
            true
        } ?: false)

    companion object {
        @Volatile
        private var instance: BasprogRepository? = null

        fun getInstance(): BasprogRepository =
            instance ?: synchronized(this) {
                instance ?: BasprogRepository().apply {
                    instance = this
                }
            }
    }

}