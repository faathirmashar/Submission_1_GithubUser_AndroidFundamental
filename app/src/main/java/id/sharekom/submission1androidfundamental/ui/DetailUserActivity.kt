package id.sharekom.submission1androidfundamental.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.sharekom.submission1androidfundamental.R
import id.sharekom.submission1androidfundamental.data.models.DetailUser
import id.sharekom.submission1androidfundamental.data.models.UserItem
import id.sharekom.submission1androidfundamental.databinding.ActivityDetailUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    private val viewModel: GithubViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userItem = intent.getParcelableExtra<UserItem>(DETAIL_USER)

        val dialogProgressbar = Dialog(this)
        dialogProgressbar.setContentView(R.layout.dialog_progressbar)
        dialogProgressbar.setCancelable(false)
        dialogProgressbar.window?.setBackgroundDrawableResource(android.R.color.transparent)

        if (userItem?.login != null) {
            dialogProgressbar.show()
            viewModel.getDetail(userItem.login).observe(this@DetailUserActivity) { detailUser ->
                dialogProgressbar.dismiss()
                showData(detailUser)
            }
        }
    }

    private fun showData(detailUser: DetailUser) {
        binding.apply {
            detailUser.apply {
                Glide.with(this@DetailUserActivity)
                    .load(avatar_url)
                    .into(ivDetailUser)

                tvName.text = name
                tvUsername.text = getString(R.string.item_username, login)
                tvFollowers.text = getString(R.string.followers, followers)
                tvFollowing.text = getString(R.string.following, following)
                tvCompany.text = getString(R.string.company, company)
                tvLocation.text = getString(R.string.location, location)
                tvRepo.text = getString(R.string.repository, public_repos)
            }
        }
    }

    companion object {
        const val DETAIL_USER = "detail_user"
    }
}