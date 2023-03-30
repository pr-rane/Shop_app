package com.example.shop_app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop_app.R
import com.example.shop_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var productAdapter: ProductAdapter
    private var categoryName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        productAdapter = ProductAdapter{ openProduct(it) }

        arguments?.let {
            categoryName = it.getString(resources.getString(R.string.arg_category_name))
        }

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding?.productRecycler?.layoutManager = GridLayoutManager(context,2)
        _binding?.productRecycler?.adapter = productAdapter

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (categoryName==null) {
            homeViewModel.fetchProducts()
        }else {
            homeViewModel.fetchProductsByCategory(categoryName)
        }
        homeViewModel.products.observe(viewLifecycleOwner) {
            productAdapter.updateProductList(it)
            Log.e("productID:",it.get(0).id.toString())
        }
    }

    fun openProduct(productId: Int){
        findNavController().navigate(
            R.id.action_home_openProduct,
            bundleOf(
                resources.getString(R.string.arg_product_id) to productId
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}