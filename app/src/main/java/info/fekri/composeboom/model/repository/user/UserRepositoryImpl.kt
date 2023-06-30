package info.fekri.composeboom.model.repository.user

import android.content.SharedPreferences
import info.fekri.composeboom.util.KEY_SUB_KIDS
import info.fekri.composeboom.util.KEY_SUB_POEMS
import info.fekri.composeboom.util.KEY_SUB_SCIENCE
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
    override fun saveUserID(id: String?) {
        sharedPreferences.edit().apply {
            putString(KEY_USER_ID, id)
        }.apply()
    }

    override fun getScienceSub(): String? = sharedPreferences.getString(KEY_SUB_SCIENCE, null)
    override fun saveScienceSub(sub: String?) {
        sharedPreferences.edit().apply {
            putString(KEY_SUB_SCIENCE, sub)
        }.apply()
    }

    override fun getKidsSub(): String? = sharedPreferences.getString(KEY_SUB_KIDS, null)
    override fun saveKidsSub(sub: String?) {
        sharedPreferences.edit().apply {
            putString(KEY_SUB_KIDS, sub)
        }.apply()
    }

    override fun getPoemsSub(): String? = sharedPreferences.getString(KEY_SUB_POEMS, null)
    override fun savePoems(sub: String?) {
        sharedPreferences.edit().apply {
            putString(KEY_SUB_POEMS, sub)
        }.apply()
    }

}