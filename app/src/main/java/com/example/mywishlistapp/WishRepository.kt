package com.example.mywishlistapp

import kotlinx.coroutines.flow.Flow


class WishRepository(private val wishDao: WishDao) {

    suspend fun addAWish(wish: Wish){
        wishDao.addWish(wish)
    }

    fun getWishes() : Flow<List<Wish>> = wishDao.getAllWishes()

    fun wishById(id : Long) :Flow<Wish>{
        return wishDao.getWishesById(id)
    }

    suspend fun deleteWish(wish:Wish){
        wishDao.deleteWish(wish)
    }

    suspend fun updateWish(wish:Wish){
        wishDao.updateWish(wish)
    }
}