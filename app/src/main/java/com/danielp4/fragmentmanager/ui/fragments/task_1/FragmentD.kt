package com.danielp4.fragmentmanager.ui.fragments.task_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.danielp4.fragmentmanager.R
import com.danielp4.fragmentmanager.databinding.FragmentDBinding
import com.danielp4.fragmentmanager.utils.SupportFragmentManager


class FragmentD : Fragment() {

    private lateinit var binding: FragmentDBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bPrev.setOnClickListener {
            SupportFragmentManager.previousFragment(null, requireActivity())
        }
        binding.toB.setOnClickListener {
            SupportFragmentManager.previousFragment(getString(R.string.fragment_b), requireActivity())
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = FragmentD()
    }
}