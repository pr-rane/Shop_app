package com.example.shop_app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shop_app.MainActivity
import com.example.shop_app.R
import com.example.shop_app.ShopApplication
import com.example.shop_app.databinding.FragmentHomeBinding
import com.example.shop_app.viewmodels.HomeViewModel
import com.example.shop_app.viewmodels.LoginViewModel

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
//        val api = ShopClient.publicApi
//        val productsRepo = ProductsRepo(api)
//        homeViewModel = ViewModelProvider(this,ViewModelFactory(productsRepo)).get(HomeViewModel::class.java)
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
//        homeViewModel = (requireActivity().application as ShopApplication).activityComponent.getHomeVM()
        val viewModelFactory = (activity as MainActivity).viewModelFactory
        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

//        if (categoryName==null) {
//
//        }else {
            homeViewModel.fetchProductsByCategory(categoryName)
//        }
        homeViewModel.products.observe(viewLifecycleOwner) {
            productAdapter.updateProductList(it)
            Log.e("productID:",it.get(0).id.toString())
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
        _binding = null
    }
}