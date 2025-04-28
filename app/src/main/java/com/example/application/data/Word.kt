package com.example.application.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_data_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "word_id")
    var id: Int,

    @ColumnInfo(name = "word_english")
    var wordEnglish: String,

    @ColumnInfo(name = "word_russian")
    var wordRussian: String
)