package id.sharekom.submission1androidfundamental.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import id.sharekom.submission1androidfundamental.data.models.UserItem
import id.sharekom.submission1androidfundamental.databinding.DialogSearchBinding

class GithubSearchDialog private constructor(context: Context, private val query: String, private val viewModel: GithubViewModel, private val lifecycleOwner: LifecycleOwner): Dialog(context), UserListAdapter.OnItemClickListener {
    private var _binding: DialogSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: UserListAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DialogSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserListAdapter()
        adapter.setOnItemClickListener(this)

        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT)

        binding.pbDialogSearch.visibility = View.VISIBLE
        viewModel.searchUsers(query).observe(lifecycleOwner) {
            binding.pbDialogSearch.visibility = View.INVISIBLE
            adapter.setData(it as ArrayList<UserItem>)
            adapter.notifyDataSetChanged()
        }

        binding.btnClose.setOnClickListener { this.dismiss() }

        initRecyclerView(adapter)
    }

    private fun initRecyclerView(adapter: UserListAdapter) {
        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.setHasFixedSize(true)
        binding.rvSearch.adapter = adapter
    }

    class Builder(private val context: Context) {
        private lateinit var searchQuery: String
        private lateinit var viewModel: GithubViewModel
        private lateinit var lifecycleOwner: LifecycleOwner

        fun setSearchQuery(searchQuery: String): Builder {
            this.searchQuery = searchQuery
            return this
        }

        fun setViewModel(viewModel: GithubViewModel): Builder {
            this.viewModel = viewModel
            return this
        }

        fun setLifecycleOwner(lifecycleOwner: LifecycleOwner): Builder {
            this.lifecycleOwner = lifecycleOwner
            return this
        }

        fun show() = GithubSearchDialog(context, searchQuery, viewModel, lifecycleOwner).show()
    }

    override fun setOnItemClickListener(userItem: UserItem) {
        val intent = Intent(context, DetailUserActivity::class.java)
        intent.putExtra(DetailUserActivity.DETAIL_USER, userItem)
        context.startActivity(intent)
    }

    override fun dismiss() {
        super.dismiss()
        _binding = null
    }
}