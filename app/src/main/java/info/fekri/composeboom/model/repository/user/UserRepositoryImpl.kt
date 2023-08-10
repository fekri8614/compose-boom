package info.fekri.composeboom.model.repository.user

import android.content.SharedPreferences
import info.fekri.composeboom.util.KEY_PROFILE_IMAGE
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

    override fun getProfileImage(): String? {
        return sharedPreferences.getString(KEY_PROFILE_IMAGE, null)
    }

    override fun saveProfileImage(img: String?) {
        sharedPreferences.edit().apply {
            putString(KEY_PROFILE_IMAGE, img)
        }.apply()
    }

    override fun getScienceSub(): Boolean = sharedPreferences.getBoolean(KEY_SUB_SCIENCE, false)
    override fun saveScienceSub(sub: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(KEY_SUB_SCIENCE, sub)
        }.apply()
    }

    override fun getKidsSub(): Boolean = sharedPreferences.getBoolean(KEY_SUB_KIDS, false)
    override fun saveKidsSub(sub: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(KEY_SUB_KIDS, sub)
        }.apply()
    }

    override fun getPoemsSub(): Boolean = sharedPreferences.getBoolean(KEY_SUB_POEMS, false)
    override fun savePoems(sub: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(KEY_SUB_POEMS, sub)
        }.apply()
    }

}