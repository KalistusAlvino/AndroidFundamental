package com.example.githubuser.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.data.response.GitHubResponse
import com.example.githubuser.data.response.ItemsItem
import com.example.githubuser.data.retrofit.ApiConfig
import com.example.githubuser.data.viewModel.DetailViewModel
import com.example.githubuser.databinding.FragmentFollowBinding
import com.example.githubuser.ui.DetailUserActivity
import com.example.githubuser.ui.MainActivity
import com.example.githubuser.ui.UserAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowFragment : Fragment() {
    private var position: Int? = null
    private var username: String? = null
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.rvFragmentGitusers.layoutManager = LinearLayoutManager(requireActivity())

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }
        if (position == 1){
            viewModel.cariListFollower(username.toString())
            username.let { viewModel.listFollowers.observe(viewLifecycleOwner){
                setListFollowers(it)
                }
            }
        } else {
            viewModel.cariListFollowing(username.toString())
            username.let {
                viewModel.listFollowing.observe(viewLifecycleOwner) {
                    setListFollowing(it)
                }
            }
        }
        return binding.root
    }
    private fun setListFollowers(user: List<ItemsItem>){
        val adapter = UserAdapter()
        adapter.submitList(user)
        binding.rvFragmentGitusers.adapter = adapter
    }
    private fun setListFollowing(user: List<ItemsItem>){
        val adapter = UserAdapter()
        adapter.submitList(user)
        binding.rvFragmentGitusers.adapter = adapter
    }

    companion object {
        const val ARG_USERNAME = "username"
        const val ARG_POSITION = "position"
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}