package id.sharekom.submission1androidfundamental.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.sharekom.submission1androidfundamental.R
import id.sharekom.submission1androidfundamental.data.models.UserItem
import id.sharekom.submission1androidfundamental.databinding.ItemUserBinding

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    private var userListData = ArrayList<UserItem>()
    private lateinit var onItemClickListener: OnItemClickListener

    fun setData(userListData: ArrayList<UserItem>) {
        this.userListData = userListData
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(userListData[position])
    }

    override fun getItemCount(): Int = userListData.size

    inner class UserListViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(userItem: UserItem) {
            binding.apply {
                Glide.with(root.context)
                    .load(userItem.avatar_url)
                    .into(ivUser)

                tvUsername.text = root.context.getString(R.string.item_username, userItem.login)
                tvId.text = root.context.getString(R.string.item_id, userItem.id)
                tvType.text = root.context.getString(R.string.item_type, userItem.type)

                root.setOnClickListener { onItemClickListener.setOnItemClickListener(userItem) }
            }
        }
    }

    interface OnItemClickListener {
        fun setOnItemClickListener(userItem: UserItem)
    }
}