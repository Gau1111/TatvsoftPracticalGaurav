package info.softweb.gauravtatvsofttest.repositories

import info.softweb.gauravtatvsofttest.network.UsersApi

class UserRepository(private val usersApi: UsersApi) {

    suspend fun getAllUsers(page:Int) = usersApi.getAllUsers(page)

}