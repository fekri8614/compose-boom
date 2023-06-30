package info.fekri.composeboom.model.repository.user

interface UserRepository {

    fun getUserName(): String?
    fun saveUserName(name: String)

    fun getUserID(): String?
    fun saveUserID(id: String?)

    fun getScienceSub(): String?
    fun saveScienceSub(sub: String?)

    fun getKidsSub(): String?
    fun saveKidsSub(sub: String?)

    fun getPoemsSub() : String?
    fun savePoems(sub: String?)

}