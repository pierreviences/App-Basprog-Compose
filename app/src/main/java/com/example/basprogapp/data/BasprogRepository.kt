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

    fun getBasprog(): Flow<List<Basprog>> {
        return flowOf(listBasprog)
    }
    fun searchBasprog(query: String): Flow<List<Basprog>> {
        return flowOf(listBasprog.filter { it.name.contains(query, ignoreCase = true) })
    }

    fun getBasprogById(id: String): Basprog {
        return listBasprog.first {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: BasprogRepository? = null

        fun getInstance(): BasprogRepository =
            instance ?: synchronized(this) {
                BasprogRepository().apply {
                    instance = this
                }
            }
    }

}