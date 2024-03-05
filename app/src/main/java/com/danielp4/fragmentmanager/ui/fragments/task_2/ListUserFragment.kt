package com.danielp4.fragmentmanager.ui.fragments.task_2

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import com.danielp4.fragmentmanager.UserViewModel
import com.danielp4.fragmentmanager.adapters.UserAdapter
import com.danielp4.fragmentmanager.data.UserModel
import com.danielp4.fragmentmanager.databinding.FragmentListUserBinding
import com.danielp4.fragmentmanager.ui.activities.SecondTaskActivity
import com.danielp4.fragmentmanager.ui.fragments.task_1.FragmentB
import com.danielp4.fragmentmanager.ui.fragments.task_1.FragmentC
import com.danielp4.fragmentmanager.utils.Constants.BUNDLE_USER_KEY
import com.danielp4.fragmentmanager.utils.Constants.REQUEST_USER_KEY
import com.danielp4.fragmentmanager.utils.SupportFragmentManager
import com.danielp4.fragmentmanager.utils.getSerializableCompat

class ListUserFragment : Fragment(), UserAdapter.Listener {

    lateinit var binding: FragmentListUserBinding
    private lateinit var adapter: UserAdapter
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQUEST_USER_KEY) { key, bundle ->
            val editUser = bundle.getSerializableCompat(BUNDLE_USER_KEY, UserModel::class.java)
            viewModel.listUser.value!![viewModel.currentPosition.value!!] = editUser
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding) {
        adapter = UserAdapter(this@ListUserFragment)
        rcView.layoutManager = GridLayoutManager(requireActivity(), 2)
        rcView.adapter = adapter
        adapter.submitList(viewModel.listUser.value)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListUserFragment()
    }

    override fun onClick(user: UserModel) {
        viewModel.currentPosition.value = viewModel.listUser.value?.indexOf(user)
        SupportFragmentManager.nextFragment2(
            UserEditInfoFragment.newInstance(user),
            requireActivity()
        )
    }
}