package id.sharekom.submission1androidfundamental.data.models

data class DataUser(
    val incomplete_results: Boolean? = false,
    val items: List<UserItem>? = ArrayList(),
    val total_count: Int? = 0
)