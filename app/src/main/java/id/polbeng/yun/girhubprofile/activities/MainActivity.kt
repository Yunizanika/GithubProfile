package id.polbeng.yun.girhubprofile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.request.RequestOptions
import id.polbeng.yun.girhubprofile.GlideApp
import id.polbeng.yun.girhubprofile.R
import id.polbeng.yun.girhubprofile.databinding.ActivityMainBinding
import id.polbeng.yun.girhubprofile.helpers.Config
import id.polbeng.yun.girhubprofile.models.GithubUser
import id.polbeng.yun.girhubprofile.services.GithubUserService
import id.polbeng.yun.girhubprofile.services.ServiceBuilder
import id.polbeng.yun.girhubprofile.viewmodels.MainViewModel
import id.polbeng.yun.girhubprofile.viewmodels.MainViewModel.Companion.TAG
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

//    companion object {
//        val TAG: String = MainActivity::class.java.simpleName
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
        mainViewModel.githubUser.observe(this) { user ->
            setUserData(user)
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.btnSearchUserLogin.setOnClickListener {
            val userLogin = binding.etSearchUserLogin.text.toString()
            var query = Config.DEFAULT_USER_LOGIN
            if (userLogin.isNotEmpty()) {
                query = userLogin
            }
            mainViewModel.searchUser(query)
        }

//        searchUser(Config.DEFAULT_USER_LOGIN)
    }
//    private fun searchUser(query: String){
//        showLoading(true)
//        Log.d(TAG, "getDataUserProfileFromAPI: start...")
//        val githubUserService: GithubUserService = ServiceBuilder.buildService(GithubUserService::class.java)
//        val requestCall: Call<GithubUser> = githubUserService.loginUser(query)
//
//        requestCall.enqueue(object : retrofit2.Callback<GithubUser> {
//            override fun onResponse(call: Call<GithubUser>, response: retrofit2.Response<GithubUser>) {
//                showLoading(false)
//                if(response.isSuccessful){
//                    val result = response.body()
//                    if (result != null) {
//                        setUserData(result)
//                    }
//                    Log.d(TAG, "getDataUserFromAPI: onResponse finish...")
//                }else{
//                    binding.tvUser.text = "User Not Found"
//                    GlideApp.with(applicationContext)
//                        .load(R.drawable.ic_baseline_broken_image_24)
//                        .into(binding.imgUser)
//                    Log.d(TAG, "getDataUserFromAPI: onResponse failed...")
//                }
//            }
//            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
//                showLoading(false)
//                Log.d(TAG, "getDataUserFromAPI: onFailure ${t.message}...")
//            }
//        })
//    }
    private fun setUserData(githubUser: GithubUser) {
        binding.tvUser.text = githubUser.toString()
        GlideApp.with(applicationContext)
            .load(githubUser.avatarUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .error(R.drawable.ic_baseline_broken_image_24))
            .into(binding.imgUser)
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
