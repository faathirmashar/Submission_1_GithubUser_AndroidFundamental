package id.sharekom.submission1androidfundamental.data.remote

import id.sharekom.submission1androidfundamental.BuildConfig
import id.sharekom.submission1androidfundamental.data.models.DataUser
import id.sharekom.submission1androidfundamental.data.models.DetailUser
import id.sharekom.submission1androidfundamental.data.models.UserItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Authorization: ${BuildConfig.API_KEY}")
    @GET("users")
    fun getUsers(): Call<List<UserItem>>

    @Headers("Authorization: ${BuildConfig.API_KEY}")
    @GET("users/{login}")
    fun getDetail(@Path("login") login: String): Call<DetailUser>

    @Headers("Authorization: ${BuildConfig.API_KEY}")
    @GET("search/users")
    fun searchUser(@Query("q") query: String): Call<DataUser>
}