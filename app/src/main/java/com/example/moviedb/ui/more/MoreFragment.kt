package com.example.moviedb.ui.more

import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentMoreBinding
import com.example.moviedb.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoreFragment : BaseFragment<FragmentMoreBinding, MoreViewModel>() {
    override val viewModel by viewModel<MoreViewModel>()
    override val layoutId: Int = R.layout.fragment_more

    companion object {
        fun newInstance() = MoreFragment()
    }
}
