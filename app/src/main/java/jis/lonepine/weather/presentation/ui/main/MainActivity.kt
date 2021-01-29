package jis.lonepine.weather.presentation.ui.main

import jis.lonepine.weather.R
import jis.lonepine.weather.databinding.MainActivityBinding
import jis.lonepine.weather.presentation.ui.base.BindingActivity
import jis.lonepine.weather.presentation.ui.dialog.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BindingActivity<MainActivityBinding>(R.layout.main_activity) {

    private var loadingDialog:LoadingDialog? = null
    private val viewModel:MainViewModel by viewModel()
    override fun initView() {
        binding.viewModel = viewModel
        loadingDialog = LoadingDialog(this)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.searchCity()
        }

        viewModel.showLoadingDialog.observe(this)
        {
            loadingDialog?.show()
        }

        viewModel.dismissLoadingDialog.observe(this)
        {
            loadingDialog?.dismiss()
        }

        viewModel.dataLoadFinish.observe(this)
        {
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.searchCity()
    }
}