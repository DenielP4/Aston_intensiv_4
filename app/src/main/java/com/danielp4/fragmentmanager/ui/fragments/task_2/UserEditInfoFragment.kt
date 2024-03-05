package com.danielp4.fragmentmanager.ui.fragments.task_2

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.danielp4.fragmentmanager.R
import com.danielp4.fragmentmanager.data.UserModel
import com.danielp4.fragmentmanager.databinding.FragmentUserEditInfoBinding
import com.danielp4.fragmentmanager.utils.Constants.BUNDLE_USER_KEY
import com.danielp4.fragmentmanager.utils.Constants.CURRENT_IMAGE_URL
import com.danielp4.fragmentmanager.utils.Constants.POSITION_KEY
import com.danielp4.fragmentmanager.utils.Constants.REQUEST_USER_KEY
import com.danielp4.fragmentmanager.utils.SupportFragmentManager
import com.danielp4.fragmentmanager.utils.getSerializableCompat
import com.squareup.picasso.Picasso


class UserEditInfoFragment : Fragment() {

    lateinit var binding: FragmentUserEditInfoBinding
    private var currentImageUrl = -1
    private var isSpinnerInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserEditInfoBinding.inflate(inflater, container, false)

        savedInstanceState?.let {
            currentImageUrl = it.getInt(CURRENT_IMAGE_URL, 0)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding) {
        setUserInfo()
        setSpinner()
        icBack.setOnClickListener {
            backFragment()
        }
        bSave.setOnClickListener {
            saveUserInfo()
        }
    }

    private fun setUserInfo() = with(binding) {
        val currentUser = requireArguments().getSerializableCompat(POSITION_KEY, UserModel::class.java)
        if (currentImageUrl == -1) {
            currentImageUrl = resources.getStringArray(R.array.list_image_url).indexOf(currentUser.image)
        }
        val newImageUrl = resources.getStringArray(R.array.list_image_url)[currentImageUrl]
        Picasso.get()
            .load(newImageUrl)
            .into(imgUser)
        edFirstName.text = Editable.Factory.getInstance().newEditable(currentUser.firstName)
        edSecondName.text = Editable.Factory.getInstance().newEditable(currentUser.secondName)
        edPhoneNumber.text = Editable.Factory.getInstance().newEditable(currentUser.phoneNumber)
    }

    private fun setSpinner() = with(binding) {
        val spinner = spinner
        val imageUrlArray = resources.getStringArray(R.array.list_image_url)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, imageUrlArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(currentImageUrl)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!isSpinnerInitialized) {
                    isSpinnerInitialized = true
                    return
                }
                currentImageUrl = position
                val newImageUrl = imageUrlArray[position]
                Picasso.get()
                    .load(newImageUrl)
                    .into(imgUser)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    private fun backFragment() {
        SupportFragmentManager.previousFragment(null, requireActivity())
    }

    private fun saveUserInfo() = with(binding) {
        if (edFirstName.text.toString().isEmpty() || edSecondName.text.toString().isEmpty() || edPhoneNumber.text.toString().isEmpty()) {
            Toast.makeText(activity as AppCompatActivity, getString(R.string.warning), Toast.LENGTH_LONG).show()
            return
        }
        setFragmentResult(
            REQUEST_USER_KEY,
            bundleOf(BUNDLE_USER_KEY to getEditUser())
        )
        backFragment()
    }

    private fun getEditUser() : UserModel = with(binding) {
        return UserModel(
            image = resources.getStringArray(R.array.list_image_url)[currentImageUrl],
            firstName = edFirstName.text.toString(),
            secondName = edSecondName.text.toString(),
            phoneNumber = edPhoneNumber.text.toString(),
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_IMAGE_URL, currentImageUrl)
    }

    companion object {
        @JvmStatic
        fun newInstance(userModel: UserModel): UserEditInfoFragment {
            return UserEditInfoFragment().apply {
                arguments = Bundle().also { it.putSerializable(POSITION_KEY, userModel) }
            }
        }
    }
}