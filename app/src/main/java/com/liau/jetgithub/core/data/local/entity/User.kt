package com.liau.jetgithub.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Budiman on 27/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Entity(tableName = "User")
data class User(
    @PrimaryKey
    val login: String,
    val name: String,
    val location: String,
    val email: String,
    val company: String,
    val avatarUrl: String,
    @ColumnInfo(defaultValue = "0")
    val follower: Int,
    @ColumnInfo(defaultValue = "0")
    val following: Int,
    @ColumnInfo(defaultValue = "0")
    val repositories: Int,
)
