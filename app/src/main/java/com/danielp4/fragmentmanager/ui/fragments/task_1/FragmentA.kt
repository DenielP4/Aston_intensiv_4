package com.danielp4.fragmentmanager.ui.fragments.task_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.danielp4.fragmentmanager.R
import com.danielp4.fragmentmanager.databinding.FragmentABinding
import com.danielp4.fragmentmanager.utils.SupportFragmentManager


class FragmentA : Fragment() {

    private lateinit var binding: FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bNext.setOnClickListener {
            SupportFragmentManager.nextFragment(FragmentB.newInstance(), getString(R.string.fragment_a), requireActivity())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = FragmentA()
    }
}