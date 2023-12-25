package com.example.shop_app.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shop_app.R
import com.example.shop_app.databinding.FragmentHomeBinding
import com.example.shop_app.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var binding: FragmentHomeBinding?=null

    private lateinit var productAdapter: ProductAdapter
    private var categoryName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdapter = ProductAdapter { openProduct(it) }


        arguments?.let {
            categoryName = it.getString(resources.getString(R.string.arg_category_name))
        }
        binding?.productRecycler?.let {
            it.layoutManager = GridLayoutManager(context, 2)
            it.adapter = productAdapter
        }
        homeViewModel.fetchProductsByCategory(categoryName)
        binding?.productsSwipeRefreshLayout?.setOnRefreshListener(this)
        lifecycleScope.launch {
            homeViewModel.products.collect{
                when (it) {
                    is UiState.Not_Started ->homeViewModel.fetchProductsByCategory(categoryName)
                    is UiState.Success -> {
                        productAdapter.updateProductList(it.data)
                        stopShimmer()
                    }
                    is UiState.Error -> {
                        stopShimmer()
                        Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        startShimmer()
                    }
                }
            }
        }
    }

    private fun openProduct(productId: Int){
        findNavController().navigate(
            R.id.action_home_openProduct,
            bundleOf(
                resources.getString(R.string.arg_product_id) to productId
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onRefresh() {
        when (homeViewModel.products.value) {
            is UiState.Error ->{
                homeViewModel.fetchProductsByCategory(categoryName)
            }
            else -> {}
        }
        binding?.productsSwipeRefreshLayout?.isRefreshing = false
    }
    private fun startShimmer() {
        binding?.productsShimmer?.startShimmer()
        binding?.productsShimmer?.visibility = View.VISIBLE
        binding?.productsSwipeRefreshLayout?.visibility = View.GONE
    }

    private fun stopShimmer() {
        binding?.productsShimmer?.stopShimmer()
        binding?.productsShimmer?.visibility = View.GONE
        binding?.productsSwipeRefreshLayout?.visibility = View.VISIBLE
        binding?.productsSwipeRefreshLayout?.isRefreshing = false
    }
}