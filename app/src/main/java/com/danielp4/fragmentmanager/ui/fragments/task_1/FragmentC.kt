package com.danielp4.fragmentmanager.ui.fragments.task_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.danielp4.fragmentmanager.R
import com.danielp4.fragmentmanager.databinding.FragmentCBinding
import com.danielp4.fragmentmanager.utils.Constants.MESSAGE_KEY
import com.danielp4.fragmentmanager.utils.SupportFragmentManager



class FragmentC : Fragment() {

    private lateinit var binding: FragmentCBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = requireArguments().getString(MESSAGE_KEY)
        binding.tvMessage.text = message
        binding.bNext.setOnClickListener {
            SupportFragmentManager.nextFragment(FragmentD.newInstance(), getString(R.string.fragment_c), requireActivity())
        }
        binding.bPrev.setOnClickListener {
            SupportFragmentManager.previousFragment(null, requireActivity())
        }
        binding.toA.setOnClickListener {
            SupportFragmentManager.previousFragment(getString(R.string.fragment_a), requireActivity())
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(message: String): FragmentC {
            return FragmentC().apply {
                arguments = Bundle().also { it.putString(MESSAGE_KEY, message) }
            }
        }
    }
}