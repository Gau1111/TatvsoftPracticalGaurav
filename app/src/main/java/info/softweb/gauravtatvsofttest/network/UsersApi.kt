package info.softweb.gauravtatvsofttest.network

import info.softweb.gauravtatvsofttest.model.UserListResponseModel
import info.softweb.gauravtatvsofttest.utils.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    //https://reqres.in/api/users?page=1
    @GET("users")
    suspend fun getAllUsers(@Query("page") page:Int): Response<UserListResponseModel>

    companion object {
        var retrofitService: UsersApi? = null
        fun getInstance() : UsersApi {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(UsersApi::class.java)
            }
            return retrofitService!!
        }

    }
}