package com.amosh.feature.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.amosh.base.BaseFragment
import com.amosh.feature.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * Main Fragment
 */
@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {

    }

}



