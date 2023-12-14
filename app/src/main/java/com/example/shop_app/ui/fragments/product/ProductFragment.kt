package com.example.shop_app.ui.fragments.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.shop_app.ui.MainActivity
import com.example.shop_app.R
import com.example.shop_app.databinding.FragmentProductBinding
import com.example.shop_app.ui.base.UiState
import com.example.shop_app.ui.extensions.getActivityComponent
import com.example.shop_app.ui.extensions.loadImage
import com.example.shop_app.ui.viewmodels.LoginViewModel
import com.example.shop_app.ui.viewmodels.ProductViewModel
import kotlinx.coroutines.launch

class ProductFragment : Fragment() {

//    private lateinit var viewModel: ProductViewModel

    private val viewModel: ProductViewModel by viewModels {
        getActivityComponent().viewModelsFactory()
    }
    private var _binding: FragmentProductBinding? = null
    private var productId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater,container,false)
//        val api = ShopClient.publicApi
//        val productsRepo = ProductsRepo(api)
//        viewModel = ViewModelProvider(this,ViewModelFactory(productsRepo)).get(ProductViewModel::class.java)
//        viewModel = (requireActivity().application as ShopApplication).activityComponent.getProductVM()
//        val viewModelFactory = (activity as MainActivity).viewModelFactory
//        viewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]

        arguments?.let {
            productId = it.getInt(resources.getString(R.string.arg_product_id))
        }

        productId?.let {
            viewModel.fetchProduct(it)
        }

        return _binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch {
            viewModel.product.collect{
                if (it is UiState.Success){
                    it.data.let {product ->
                        _binding?.apply {
                            productName.text = product.title
                            productImage.loadImage(product.image)
                            productPrice.text = product.price.toString()
                            productDescription.text = product.description.toString()
                            productRate.rating = product.rating?.count?.toFloat() ?: 0.0f
                            productRateCount.text = product.rating?.rate.toString()
                        }
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}