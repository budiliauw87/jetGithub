package com.liau.jetgithub.core.data.local.database

import androidx.paging.PagingSource
import androidx.room.*
import com.liau.jetgithub.core.data.local.entity.User


/**
 * Created by Budiman on 27/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(user: User)
    @Delete
    fun deleteFavorite(user: User)
    @Query("SELECT COUNT(login) FROM User WHERE login =:loginId")
    fun countUser(loginId: String): Int
    @Query("SELECT * from User")
    fun getUserFavorite(): PagingSource<Int, User>


}