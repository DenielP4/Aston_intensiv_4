package com.danielp4.fragmentmanager.ui.fragments.task_1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielp4.fragmentmanager.R
import com.danielp4.fragmentmanager.databinding.FragmentBBinding
import com.danielp4.fragmentmanager.utils.SupportFragmentManager


class FragmentB : Fragment() {

    private lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = getString(R.string.message)
        binding.bPrev.setOnClickListener {
            SupportFragmentManager.previousFragment(null, requireActivity())
        }
        binding.bNext.setOnClickListener {
            SupportFragmentManager.nextFragment(FragmentC.newInstance(message), getString(R.string.fragment_b), requireActivity())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = FragmentB()
    }
}