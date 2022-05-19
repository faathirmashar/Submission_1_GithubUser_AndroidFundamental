package id.sharekom.submission1androidfundamental.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.sharekom.submission1androidfundamental.data.models.DataUser
import id.sharekom.submission1androidfundamental.data.models.DetailUser
import id.sharekom.submission1androidfundamental.data.models.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {
    fun getUsers(): LiveData<List<UserItem>> {
        val data = MutableLiveData<List<UserItem>>()

        apiService.getUsers().enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                val body = response.body()
                data.postValue(body)
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return data
    }

    fun getDetail(login: String): LiveData<DetailUser> {
        val data = MutableLiveData<DetailUser>()

        apiService.getDetail(login).enqueue(object : Callback<DetailUser> {
            override fun onResponse(
                call: Call<DetailUser>,
                response: Response<DetailUser>
            ) {
                val body = response.body()
                data.postValue(body)
            }

            override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return data
    }

    fun searchUser(query: String): LiveData<List<UserItem>> {
        val data = MutableLiveData<List<UserItem>>()

        apiService.searchUser(query).enqueue(object : Callback<DataUser> {
            override fun onResponse(
                call: Call<DataUser>,
                response: Response<DataUser>
            ) {
                val body = response.body()
                data.postValue(body?.items)
            }

            override fun onFailure(call: Call<DataUser>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return data
    }
}