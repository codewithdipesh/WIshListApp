package com.example.mywishlistapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wishEntity : Wish)

    @Query("SELECT * FROM `wish-table`")
    abstract fun getAllWishes() : Flow<List<Wish>>

    @Update
    abstract suspend fun updateWish(wishEntity : Wish)

    @Delete
    abstract suspend fun deleteWish(wishEntity : Wish)

    @Query("SELECT * FROM `wish-table` where id = :id")
    abstract  fun getWishesById(id : Long) : Flow<Wish>
}