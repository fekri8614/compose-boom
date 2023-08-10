package info.fekri.composeboom.model.repository.user

interface UserRepository {

    fun getUserName(): String?
    fun saveUserName(name: String)

    fun getUserID(): String?
    fun saveUserID(id: String?)

    fun getProfileImage(): String?
    fun saveProfileImage(img: String?)

    fun getScienceSub(): Boolean
    fun saveScienceSub(sub: Boolean)

    fun getKidsSub(): Boolean
    fun saveKidsSub(sub: Boolean)

    fun getPoemsSub() : Boolean
    fun savePoems(sub: Boolean)

}