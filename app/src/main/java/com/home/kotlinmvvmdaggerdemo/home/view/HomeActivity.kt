package com.home.kotlinmvvmdaggerdemo.home.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.home.kotlinmvvmdaggerdemo.R
import com.home.kotlinmvvmdaggerdemo.common.base.BaseActivity
import com.home.kotlinmvvmdaggerdemo.common.base.BaseViewModelFactory
import com.home.kotlinmvvmdaggerdemo.common.utils.Constants
import com.home.kotlinmvvmdaggerdemo.details.view.DetailsActivity
import com.home.kotlinmvvmdaggerdemo.home.model.HomeBean
import com.home.kotlinmvvmdaggerdemo.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

class HomeActivity : BaseActivity(),
    HomeRecyclerViewAdapter.RecyclerViewItemClickListener {

    @Inject
    lateinit var homeViewModel: HomeViewModel
    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory

    override val layoutId: Int get() = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(homeViewModel)
    }

    override fun initializeViewModel() {
        homeViewModel = viewModelFactory.create(HomeViewModel::class.java)
    }

    override fun onRecyclerViewItemClick(position: Int) {
        navigateToDetailsScreen(result = homeViewModel.homeData.value?.result?.results!![position])
    }

    private fun navigateToDetailsScreen(result: HomeBean.Results.Result) {
        startActivity(intentFor<DetailsActivity>(Constants.HOME_RECYCLER_VIEW_ITEM_KEY to result))
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.stay)
    }

    private fun init(viewModel: HomeViewModel) {
        viewModel.noInterNetConnection.observe(this, Observer {
            if (it) {
                recycler_view.visibility = GONE
                toast("Please check your Internet connection!")
                shimmer_view_container.stopShimmer()
                shimmer_view_container.hideShimmer()
                Glide.with(this).load(R.drawable.icon_home_unstable_network)
                    .into(image_view_loading)
            }
        })

        viewModel.showError.observe(this, Observer {
            recycler_view.visibility = GONE
            toast("" + it?.description)
            shimmer_view_container.hideShimmer()
            Glide.with(this).load(R.drawable.icon_home_unstable_network).into(image_view_loading)
        })

        viewModel.homeData.observe(this, Observer { homeModel ->
            // 由於LiveData保證了適配器的有效性，因此我們不需要對適配器進行任何null檢查
            // 如果片段已停止或未開始，它不會打電話給我們。
            // isNullOrEmpty: 如果我們不在乎只有空格的可能性
            if (!(homeModel?.result?.results.isNullOrEmpty())) {
                val newsAdapter =
                    HomeRecyclerViewAdapter(this, homeModel.result.results)
                val layoutManager = LinearLayoutManager(this)
                recycler_view.layoutManager = layoutManager
                recycler_view.setHasFixedSize(true)
                recycler_view.adapter = newsAdapter
                recycler_view.visibility = VISIBLE
            } else {
                recycler_view.visibility = GONE
                toast("some thing went wrong!")
            }
            shimmer_view_container.hideShimmer()
            image_view_loading.visibility = GONE
        })
        getHomeData()
    }

    private fun getHomeData() {
        Glide.with(this).load(R.drawable.icon_home_loading).into(image_view_loading)
        image_view_loading.visibility = VISIBLE
        shimmer_view_container.showShimmer(true)
        recycler_view.visibility = GONE
        homeViewModel.getHomeData()
    }
}
