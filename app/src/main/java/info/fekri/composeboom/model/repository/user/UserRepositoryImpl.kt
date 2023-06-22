package info.fekri.composeboom.model.repository.user

import android.content.SharedPreferences
import info.fekri.composeboom.util.KEY_USER_ID
import info.fekri.composeboom.util.KEY_USER_NAME

class UserRepositoryImpl(private val sharedPreferences: SharedPreferences) : UserRepository {

    override fun getUserName(): String? = sharedPreferences.getString(KEY_USER_NAME, null)
    override fun saveUserName(name: String) {
        sharedPreferences.edit().apply {
            putString(KEY_USER_NAME, name)
        }.apply()
    }

    override fun getUserID(): String? = sharedPreferences.getString(KEY_USER_ID, null)
    override fun saveUserID(id: String) {
        sharedPreferences.edit().apply {
            putString(KEY_USER_ID, id)
        }.apply()
    }
}