package com.example.pr5mybd.db

import androidx.room.TypeConverter
import com.example.pr5mybd.models.Source

class Converter {

    @TypeConverter
    fun fromSource(source: Source) = source.name

    @TypeConverter
    fun toSource(name: String) = Source(name, name)
}