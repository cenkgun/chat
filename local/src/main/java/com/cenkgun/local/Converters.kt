package com.cenkgun.local

import androidx.room.TypeConverter
import com.cenkgun.local.models.UserLocalModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun convertJsonToUserLocalModel(json: String): UserLocalModel? {
        val typeToken = object : TypeToken<UserLocalModel?>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun convertUserLocalModelToJson(UserLocalModel: UserLocalModel): String {
        return Gson().toJson(UserLocalModel)
    }
}