package com.example.moviedb.ui.popular

import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentPopularBinding
import com.example.moviedb.ui.base.BaseFragment
import com.example.moviedb.ui.detail.DetailMovieFragment
import com.example.moviedb.utils.LoadType
import kotlinx.android.synthetic.main.fragment_popular.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : BaseFragment<FragmentPopularBinding, PopularViewModel>(), SwipeRefreshLayout.OnRefreshListener {
    val adapter = MovieAdapter(itemClick = { goToDetail(it) })

    companion object {
        val TAG = "POPULAR"
        fun newInstance() = PopularFragment()
    }

    override val viewModel by viewModel<PopularViewModel>()
    override val layoutId: Int = R.layout.fragment_popular

    override fun initComponents(viewBinding: ViewDataBinding) {
        isShowNavigation(true)
        recycler_view_popular.adapter = adapter
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.loadDataPopular(LoadType.NORMAL)
        swipe_refresh_layout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        viewModel.loadDataPopular(LoadType.REFRESH)
        swipe_refresh_layout.isRefreshing = false
    }

    private fun goToDetail(movie: Movie?) {
        addChildFragment(DetailMovieFragment.newInstance(movie), R.id.container, DetailMovieFragment.TAG, true)
    }
}
