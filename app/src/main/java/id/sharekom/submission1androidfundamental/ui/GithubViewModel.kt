package id.sharekom.submission1androidfundamental.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.sharekom.submission1androidfundamental.data.IGithubRepository
import id.sharekom.submission1androidfundamental.data.models.DetailUser
import id.sharekom.submission1androidfundamental.data.models.UserItem

class GithubViewModel(private val githubRepository: IGithubRepository) : ViewModel() {
    fun getUsers(): LiveData<List<UserItem>> = githubRepository.getUsers()
    fun getDetail(login: String): LiveData<DetailUser> = githubRepository.getDetail(login)
    fun searchUsers(query: String): LiveData<List<UserItem>> = githubRepository.searchUser(query)
}