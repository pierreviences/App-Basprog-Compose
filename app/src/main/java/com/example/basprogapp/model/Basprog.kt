package com.example.basprogapp.model

data class Basprog (
    val id: String,
    val name: String,
    val description: String,
    val like: String,
    val creator: String,
    val years: String,
    val photoUrl: String,
    val isFavorite: Boolean = false
    )