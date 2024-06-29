package com.pvsrishabh.cookease.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pvsrishabh.cookease.domain.model.ExtendedIngredient

@ProvidedTypeConverter
class RecipesTypeConvertor {

    private val gson = Gson()

    @TypeConverter
    fun fromExtendedIngredientList(extendedIngredients: List<ExtendedIngredient>): String {
        return gson.toJson(extendedIngredients)
    }

    @TypeConverter
    fun toExtendedIngredientList(extendedIngredientsString: String): List<ExtendedIngredient> {
        val type = object : TypeToken<List<ExtendedIngredient>>() {}.type
        return gson.fromJson(extendedIngredientsString, type)
    }

    @TypeConverter
    fun extendedIngredientToString(extendedIngredient: ExtendedIngredient): String{
        return extendedIngredient.name
    }

    @TypeConverter
    fun stringToExtendedIngredient(extendedIngredient: String): ExtendedIngredient{
        return ExtendedIngredient(extendedIngredient)
    }
}