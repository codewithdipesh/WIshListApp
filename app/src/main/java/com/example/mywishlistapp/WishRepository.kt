package com.example.mywishlistapp

import kotlinx.coroutines.flow.Flow


class WishRepository(private val wishDao: WishDao) {

    suspend fun addAWish(wish: Wish){
        wishDao.addWish(wish)
    }

    fun getWishes() : Flow<List<Wish>>
}