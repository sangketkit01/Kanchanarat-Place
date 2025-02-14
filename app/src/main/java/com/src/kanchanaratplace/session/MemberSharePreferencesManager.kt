package com.src.kanchanaratplace.session

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.src.kanchanaratplace.data.Member
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemberSharePreferencesManager(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    private val gson = Gson()

    var loggedIn : Boolean
        get() = preferences.getBoolean(KEY_IS_LOGGED_IN,false)
        set(value) = preferences.edit().putBoolean(KEY_IS_LOGGED_IN,value).apply()

    var member: Member?
        get() {
            val json = preferences.getString(KEY_MEMBER, null)
            return json?.let { gson.fromJson(it, Member::class.java) }
        }
        set(value) {
            val json = gson.toJson(value)
            preferences.edit().putString(KEY_MEMBER, json).apply()
        }


    fun clear() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_MEMBER = "member_data"
    }
}
