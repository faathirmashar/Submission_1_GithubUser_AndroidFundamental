package id.sharekom.submission1androidfundamental.data

import androidx.lifecycle.LiveData
import id.sharekom.submission1androidfundamental.data.models.DetailUser
import id.sharekom.submission1androidfundamental.data.models.UserItem
import id.sharekom.submission1androidfundamental.data.remote.RemoteDataSource

class GithubRepository(private val remoteDataSource: RemoteDataSource) : IGithubRepository {
    override fun getUsers(): LiveData<List<UserItem>> {
        return remoteDataSource.getUsers()
    }

    override fun getDetail(login: String): LiveData<DetailUser> {
        return remoteDataSource.getDetail(login)
    }

    override fun searchUser(query: String): LiveData<List<UserItem>> {
        return remoteDataSource.searchUser(query)
    }
}