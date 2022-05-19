package id.sharekom.submission1androidfundamental.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.sharekom.submission1androidfundamental.data.models.UserItem
import id.sharekom.submission1androidfundamental.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), UserListAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: UserListAdapter
    private val viewModel: GithubViewModel by viewModel()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserListAdapter()
        adapter.setOnItemClickListener(this)

        binding.pbMain.visibility = View.VISIBLE
        viewModel.getUsers().observe(this) {
            binding.pbMain.visibility = View.INVISIBLE
            adapter.setData(it as ArrayList<UserItem>)
            adapter.notifyDataSetChanged()
        }

        binding.apply {
            rvMain.setHasFixedSize(true)
            rvMain.layoutManager = LinearLayoutManager(applicationContext)
            rvMain.adapter = adapter

            tiSearchUser.setEndIconOnClickListener {
                val query = tiSearchUser.editText?.text.toString()

                if (query.isNotEmpty()) {
                    GithubSearchDialog.Builder(this@MainActivity)
                        .setSearchQuery(query)
                        .setLifecycleOwner(this@MainActivity)
                        .setViewModel(viewModel)
                        .show()
                }
            }
        }
    }

    override fun setOnItemClickListener(userItem: UserItem) {
        val intent = Intent(this, DetailUserActivity::class.java)
        intent.putExtra(DetailUserActivity.DETAIL_USER, userItem)
        startActivity(intent)
    }
}