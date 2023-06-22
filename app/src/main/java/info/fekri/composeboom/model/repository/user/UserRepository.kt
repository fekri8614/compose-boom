package info.fekri.composeboom.model.repository.user

interface UserRepository {

    fun getUserName(): String?
    fun saveUserName(name: String)

    fun getUserID(): String?
    fun saveUserID(id: String)

}