package id.sharekom.submission1androidfundamental.data

import androidx.lifecycle.LiveData
import id.sharekom.submission1androidfundamental.data.models.DetailUser
import id.sharekom.submission1androidfundamental.data.models.UserItem

interface IGithubRepository {
    fun getUsers(): LiveData<List<UserItem>>
    fun getDetail(login: String): LiveData<DetailUser>
    fun searchUser(query: String): LiveData<List<UserItem>>
}