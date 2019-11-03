package com.home.kotlinmvvmdaggerdemo.details.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.home.kotlinmvvmdaggerdemo.R
import com.home.kotlinmvvmdaggerdemo.common.base.BaseActivity
import com.home.kotlinmvvmdaggerdemo.common.base.BaseViewModelFactory
import com.home.kotlinmvvmdaggerdemo.common.utils.Constants
import com.home.kotlinmvvmdaggerdemo.details.viewmodel.DetailsViewModel
import com.home.kotlinmvvmdaggerdemo.home.model.HomeBean
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: DetailsViewModel
    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory

    override val layoutId: Int get() = R.layout.activity_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.result?.value = intent.getParcelableExtra(Constants.HOME_RECYCLER_VIEW_ITEM_KEY)
        viewModel.result?.observe(this,
            Observer { result -> initializeView(result = result) })
    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(viewModel::class.java)
    }

    private fun initializeView(result: HomeBean.Results.Result) {
        text_view_regulatory_number.text = result.regulatoryNumber
        text_view_clearing_mechanism.text = result.clearingMechanism
        text_view_work_phone.text = result.workPhone
        image_view_previous_page.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.stay, R.anim.slide_out_from_left)
    }
}
