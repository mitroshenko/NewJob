package com.mitroshenko.newjob.data.repository

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "my_table")
@Parcelize
data class Entity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "rating")
    val rating: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "images")
    val images: String,
    @ColumnInfo(name = "idCardProd")
    val idCardProd: Int
) : Parcelable


