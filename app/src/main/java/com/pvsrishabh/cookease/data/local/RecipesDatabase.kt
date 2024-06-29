package com.pvsrishabh.cookease.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pvsrishabh.cookease.data.local.RecipesDao
import com.pvsrishabh.cookease.data.local.RecipesTypeConvertor
import com.pvsrishabh.cookease.domain.model.Recipe

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(RecipesTypeConvertor::class)
abstract class RecipesDatabase() : RoomDatabase() {
    abstract val recipesDao: RecipesDao
}